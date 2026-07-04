package org.urusso.vgcteamwars.domain.team.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.urusso.vgcteamwars.common.constants.ErrorEnum;
import org.urusso.vgcteamwars.common.exception.BusinessException;
import org.urusso.vgcteamwars.domain.team.dto.CreateTeamRequest;
import org.urusso.vgcteamwars.domain.team.dto.CreateTeamResponse;
import org.urusso.vgcteamwars.domain.team.dto.FindTeamResponse;
import org.urusso.vgcteamwars.domain.team.dto.TeamResponse;
import org.urusso.vgcteamwars.domain.team.mapper.TeamMapper;
import org.urusso.vgcteamwars.persistence.model.TeamEntity;
import org.urusso.vgcteamwars.persistence.model.UserEntity;
import org.urusso.vgcteamwars.persistence.repository.TeamRepository;
import org.urusso.vgcteamwars.persistence.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final UserRepository userRepository;

    public CreateTeamResponse createTeam(CreateTeamRequest request) {
        //TODO if you already are in a team, you can't create another

        Optional<TeamEntity> teamOpt = teamRepository.findByName(request.name());
        if(teamOpt.isPresent()) throw new BusinessException(ErrorEnum.TEAM_ALREDY_PRESENT);

        TeamEntity create = teamMapper.toEntityFromCreateRequest(request);
        TeamEntity saved = teamRepository.save(create);

        Optional<UserEntity> userOpt = userRepository.findById(request.creatorId());
        UserEntity user = userOpt.orElseThrow(() -> new BusinessException(ErrorEnum.USER_NOT_FOUND));
        user.setTeamId(saved.getId());
        userRepository.save(user);

        return new CreateTeamResponse(saved.getId());
    }

    public TeamResponse getTeamById(Long teamId) {
        Optional<TeamEntity> found = teamRepository.findById(teamId);

        return found.map(teamMapper::toResponse)
                .orElseThrow(() -> new BusinessException(ErrorEnum.TEAM_NOT_FOUND));
    }

    public List<FindTeamResponse> findTeamsByNameLike(String name) {
        List<TeamEntity> entities = teamRepository.findByNameContainingIgnoreCase(name);

        return entities.stream()
                .filter(Objects::nonNull)
                .map(e -> new FindTeamResponse(e.getId(), e.getName()))
                .toList();
    }

    public TeamEntity findById(Long teamId) {
        Optional<TeamEntity> teamOpt = teamRepository.findById(teamId);
        return teamOpt.orElseThrow(() -> new BusinessException(ErrorEnum.TEAM_NOT_FOUND));
    }
}
