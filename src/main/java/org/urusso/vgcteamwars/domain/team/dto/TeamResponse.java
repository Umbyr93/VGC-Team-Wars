package org.urusso.vgcteamwars.domain.team.dto;

import java.time.LocalDateTime;

public record TeamResponse(
        Long teamId,
        String name,
        String logoUrl,
        String websiteUrl,
        String instagramUrl,
        String twitterUrl,
        Long creatorId,
        LocalDateTime dateCreation
) {}
