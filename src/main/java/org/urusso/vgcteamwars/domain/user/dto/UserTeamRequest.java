package org.urusso.vgcteamwars.domain.user.dto;

import jakarta.validation.constraints.NotNull;

public record UserTeamRequest(
        @NotNull Long userId,
        @NotNull Long teamId,
        @NotNull Long adminId
) {}
