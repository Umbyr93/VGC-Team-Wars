package org.urusso.vgcteamwars.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank String username,
        @NotBlank @Size(min = 8, max = 20) String password,
        @NotBlank String discordNickname,
        String twitterNickname,
        String fullName
) {}
