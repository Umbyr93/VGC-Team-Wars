package org.urusso.vgcteamwars.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    USER_NOT_FOUND("USER_NOT_FOUND", "Wrong username, user not found", HttpStatus.UNAUTHORIZED),
    WRONG_PASSWORD("WRONG_PASSWORD", "Wrong password", HttpStatus.UNAUTHORIZED),
    TEAM_NOT_FOUND ("TEAM_NOT_FOUND", "Team not found", HttpStatus.NOT_FOUND),
    WAR_NOT_FOUND ("WAR_NOT_FOUND", "War not found", HttpStatus.NOT_FOUND),
    WAR_SAME_TEAM_ID ("WAR_SAME_TEAM_ID", "Team1Id and Team2Id are the same", HttpStatus.INTERNAL_SERVER_ERROR),
    REFRESH_TOKEN_INVALID ("REFRESH_TOKEN_INVALID", "Refresh token is invalid", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
