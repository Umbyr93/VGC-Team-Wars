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
import org.urusso.vgcteamwars.persistence.repository.TeamRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public CreateTeamResponse createTeam(CreateTeamRequest request) {
        //todo controllo nome team già esistente

        TeamEntity create = teamMapper.toEntityFromCreateRequest(request);
        TeamEntity saved = teamRepository.save(create);

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
}
