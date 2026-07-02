package org.urusso.vgcteamwars.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    USER_NOT_FOUND("001", "Wrong username, user not found", HttpStatus.UNAUTHORIZED),
    WRONG_PASSWORD("002", "Wrong password", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
