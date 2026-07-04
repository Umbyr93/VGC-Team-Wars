package org.urusso.vgcteamwars.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TEAM")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String logoUrl;
    private String websiteUrl;
    private String instagramUrl;
    private String twitterUrl;
    private Long creatorId;
    @CreationTimestamp
    private LocalDateTime dateCreation;
}
