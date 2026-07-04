package org.urusso.vgcteamwars.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.urusso.vgcteamwars.common.constants.ErrorEnum;
import org.urusso.vgcteamwars.common.exception.AuthorizationException;
import org.urusso.vgcteamwars.domain.jwt.provider.JwtProvider;
import org.urusso.vgcteamwars.domain.user.dto.CreateUserRequest;
import org.urusso.vgcteamwars.domain.user.dto.CreateUserResponse;
import org.urusso.vgcteamwars.domain.user.dto.LoginUserRequest;
import org.urusso.vgcteamwars.domain.user.dto.LoginUserResponse;
import org.urusso.vgcteamwars.domain.user.mapper.UserMapper;
import org.urusso.vgcteamwars.persistence.model.UserEntity;
import org.urusso.vgcteamwars.persistence.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;

    public CreateUserResponse createUser(CreateUserRequest request) {
        //TODO non si può creare uno user già presente

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
}
