package org.urusso.vgcteamwars.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.urusso.vgcteamwars.common.constants.ErrorEnum;
import org.urusso.vgcteamwars.common.exception.AuthorizationException;
import org.urusso.vgcteamwars.common.exception.BusinessException;
import org.urusso.vgcteamwars.domain.jwt.provider.JwtProvider;
import org.urusso.vgcteamwars.domain.user.dto.*;
import org.urusso.vgcteamwars.domain.user.mapper.UserMapper;
import org.urusso.vgcteamwars.persistence.model.TeamEntity;
import org.urusso.vgcteamwars.persistence.model.UserEntity;
import org.urusso.vgcteamwars.persistence.repository.TeamRepository;
import org.urusso.vgcteamwars.persistence.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    private final TeamRepository teamRepository;

    public CreateUserResponse createUser(CreateUserRequest request) {
        Optional<UserEntity> user = userRepository.findByUsername(request.username());
        if(user.isPresent()) throw new BusinessException(ErrorEnum.USER_ALREDY_PRESENT);

        UserEntity createEntity = userMapper.toEntityFromCreateRequest(request);
        createEntity.setPassword(ENCODER.encode(request.password()));

        UserEntity savedEntity = userRepository.save(createEntity);
        return new CreateUserResponse(savedEntity.getId());
    }

    public LoginUserResponse login(LoginUserRequest request) {
        Optional<UserEntity> userOpt = userRepository.findByUsername(request.username());
        UserEntity user = userOpt.orElseThrow(() -> new AuthorizationException(ErrorEnum.USER_NOT_FOUND));

        if(!ENCODER.matches(request.password(), user.getPassword()))
            throw new AuthorizationException(ErrorEnum.WRONG_PASSWORD);

        LoginUserResponse response = userMapper.toResponse(user);
        response.setJwtToken(jwtProvider.generateToken(response.getUsername()));
        response.setRefreshToken(jwtProvider.generateRefreshToken(response.getUsername()));
        return response;
    }

    public void addUserToTeam(UserTeamRequest request) {
        UserEntity user = findById(request.userId());

        Optional<UserEntity> adminOpt = userRepository.findById(request.userId());
        UserEntity admin = adminOpt.orElseThrow(() -> new BusinessException(ErrorEnum.ADMIN_NOT_FOUND));

        Optional<TeamEntity> teamOpt = teamRepository.findById(request.teamId());
        TeamEntity team = teamOpt.orElseThrow(() -> new BusinessException(ErrorEnum.TEAM_NOT_FOUND));

        if((!admin.isTeamAdmin() && !team.getId().equals(admin.getTeamId())) && !admin.isPlatformAdmin())
            throw new BusinessException(ErrorEnum.NO_PERMISSIONS);

        user.setTeamId(team.getId());
        userRepository.save(user);
    }

    public UserEntity findById(Long userId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        return userOpt.orElseThrow(() -> new BusinessException(ErrorEnum.USER_NOT_FOUND));
    }
}
