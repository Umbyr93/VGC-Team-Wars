package org.urusso.vgcteamwars.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import org.urusso.vgcteamwars.common.constants.MatchEnum;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MATCH")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long warId;
    private Long player1Id;
    private String player1OtsUrl;
    private LocalDateTime player1OtsUpdate;
    private String player1CtsUrl;
    private LocalDateTime player1CtsUpdate;
    private Long player2Id;
    private String player2OtsUrl;
    private LocalDateTime player2OtsUpdate;
    private String player2CtsUrl;
    private LocalDateTime player2CtsUpdate;
    @Enumerated(EnumType.STRING)
    private MatchEnum player1Game1Result;
    @Enumerated(EnumType.STRING)
    private MatchEnum player1Game2Result;
    @Enumerated(EnumType.STRING)
    private MatchEnum player1Game3Result;
    @Enumerated(EnumType.STRING)
    private MatchEnum player2Game1Result;
    @Enumerated(EnumType.STRING)
    private MatchEnum player2Game2Result;
    @Enumerated(EnumType.STRING)
    private MatchEnum player2Game3Result;
}
