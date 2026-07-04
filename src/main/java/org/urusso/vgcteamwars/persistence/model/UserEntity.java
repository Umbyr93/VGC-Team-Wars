package org.urusso.vgcteamwars.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String discordNickname;
    private String twitterNickname;
    private String fullName;
    private Long teamId;
    private boolean platformAdmin;
    private boolean teamAdmin;
}
