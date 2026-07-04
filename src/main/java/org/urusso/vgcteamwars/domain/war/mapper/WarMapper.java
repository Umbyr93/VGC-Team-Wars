package org.urusso.vgcteamwars.domain.war.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.urusso.vgcteamwars.domain.war.dto.CreateWarRequest;
import org.urusso.vgcteamwars.domain.war.dto.WarResponse;
import org.urusso.vgcteamwars.persistence.model.WarEntity;

@Mapper(componentModel = "spring")
public interface WarMapper {
    WarEntity toEntityFromCreateRequest(CreateWarRequest source);
    @Mapping(source = "id", target = "warId")
    WarResponse toResponse(WarEntity source);
}
