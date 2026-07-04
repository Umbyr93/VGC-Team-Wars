package org.urusso.vgcteamwars.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND),
    WRONG_PASSWORD("WRONG_PASSWORD", "Wrong password", HttpStatus.UNAUTHORIZED),
    TEAM_NOT_FOUND ("TEAM_NOT_FOUND", "Team not found", HttpStatus.NOT_FOUND),
    WAR_NOT_FOUND ("WAR_NOT_FOUND", "War not found", HttpStatus.NOT_FOUND),
    WAR_SAME_TEAM_ID ("WAR_SAME_TEAM_ID", "Team1Id and Team2Id are the same", HttpStatus.CONFLICT),
    REFRESH_TOKEN_INVALID ("REFRESH_TOKEN_INVALID", "Refresh token is invalid", HttpStatus.UNAUTHORIZED),
    USER_ALREDY_PRESENT ("USER_ALREDY_PRESENT", "Username already present", HttpStatus.CONFLICT),
    TEAM_ALREDY_PRESENT ("TEAM_ALREDY_PRESENT", "Team already present", HttpStatus.CONFLICT),
    ADMIN_NOT_FOUND("ADMIN_NOT_FOUND", "Adminser not found", HttpStatus.NOT_FOUND),
    NO_PERMISSIONS("NO_PERMISSIONS", "You have no permissions to execute this action", HttpStatus.FORBIDDEN);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
