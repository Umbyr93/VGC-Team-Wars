package org.urusso.vgcteamwars.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserResponse {
    private Long userId;
    private String username;
    private String discordNickname;
    private String twitterNickname;
    private String fullName;
    private String teamId;
    private boolean platformAdmin;
    private boolean teamAdmin;
    private String jwtToken;
    private String refreshToken;
}
