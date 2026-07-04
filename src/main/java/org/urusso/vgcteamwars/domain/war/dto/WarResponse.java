package org.urusso.vgcteamwars.domain.war.dto;

import java.time.LocalDateTime;

public record WarResponse(
        Long warId,
        Long team1Id,
        Long team2Id,
        int team1Wins,
        int team2Wins,
        LocalDateTime dateStart,
        LocalDateTime dateEnd,
        boolean accepted,
        LocalDateTime dateCreation
) {}
