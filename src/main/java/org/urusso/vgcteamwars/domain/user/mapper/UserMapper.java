package org.urusso.vgcteamwars.domain.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.urusso.vgcteamwars.domain.user.dto.CreateUserRequest;
import org.urusso.vgcteamwars.domain.user.dto.LoginUserResponse;
import org.urusso.vgcteamwars.persistence.model.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "id", target = "userId")
    LoginUserResponse toResponse(UserEntity source);
    List<LoginUserResponse> toResponseList(List<UserEntity> source);
    UserEntity toEntityFromCreateRequest(CreateUserRequest source);
}
