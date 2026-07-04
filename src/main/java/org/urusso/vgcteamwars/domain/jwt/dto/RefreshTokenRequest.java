package org.urusso.vgcteamwars.domain.jwt.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank String username,
        @NotBlank String refreshToken
) {}
