package org.urusso.vgcteamwars.domain.team.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTeamRequest(
        @NotBlank String name,
        String logoUrl,
        String websiteUrl,
        String instagramUrl,
        String twitterUrl,
        @NotNull Long creatorId
) {}
