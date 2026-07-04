package org.urusso.vgcteamwars.domain.team.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.urusso.vgcteamwars.domain.team.dto.CreateTeamRequest;
import org.urusso.vgcteamwars.domain.team.dto.TeamResponse;
import org.urusso.vgcteamwars.persistence.model.TeamEntity;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamEntity toEntityFromCreateRequest(CreateTeamRequest source);
    @Mapping(source = "id", target = "teamId")
    TeamResponse toResponse(TeamEntity source);
}
