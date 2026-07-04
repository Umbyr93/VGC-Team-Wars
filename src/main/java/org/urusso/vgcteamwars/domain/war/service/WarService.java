package org.urusso.vgcteamwars.domain.war.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.urusso.vgcteamwars.common.constants.ErrorEnum;
import org.urusso.vgcteamwars.common.exception.BusinessException;
import org.urusso.vgcteamwars.domain.war.dto.CreateWarRequest;
import org.urusso.vgcteamwars.domain.war.dto.CreateWarResponse;
import org.urusso.vgcteamwars.domain.war.dto.WarResponse;
import org.urusso.vgcteamwars.domain.war.mapper.WarMapper;
import org.urusso.vgcteamwars.persistence.model.WarEntity;
import org.urusso.vgcteamwars.persistence.repository.WarRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarService {
    private final WarRepository warRepository;
    private final WarMapper warMapper;

    public CreateWarResponse createTeam(CreateWarRequest request) {
       if(request.team2Id().equals(request.team1Id()))
           throw new BusinessException(ErrorEnum.WAR_SAME_TEAM_ID);

        WarEntity create = warMapper.toEntityFromCreateRequest(request);
        WarEntity saved = warRepository.save(create);

        return new CreateWarResponse(saved.getId());
    }

    public WarResponse getTeamById(Long warId) {
        Optional<WarEntity> found = warRepository.findById(warId);

        return found.map(warMapper::toResponse)
                .orElseThrow(() -> new BusinessException(ErrorEnum.WAR_NOT_FOUND));
    }
}
