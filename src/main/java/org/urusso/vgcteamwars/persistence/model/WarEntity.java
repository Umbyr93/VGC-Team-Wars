package org.urusso.vgcteamwars.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "WAR")
public class WarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TEAM1_ID")
    private TeamEntity team1;

    @ManyToOne
    @JoinColumn(name = "TEAM2_ID")
    private TeamEntity team2;

    private Integer team1Wins;
    private Integer team2Wins;
    @CreationTimestamp
    private LocalDateTime dateCreation;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private boolean accepted;
}
