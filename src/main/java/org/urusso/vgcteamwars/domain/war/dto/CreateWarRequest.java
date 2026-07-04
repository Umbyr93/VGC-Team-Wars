package org.urusso.vgcteamwars.domain.war.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateWarRequest(
        @NotNull Long team1Id,
        @NotNull Long team2Id,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") @NotNull
        LocalDateTime dateStart,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") @NotNull
        @NotNull LocalDateTime dateEnd
) {}
