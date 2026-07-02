package org.urusso.vgcteamwars.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.urusso.vgcteamwars.common.constants.ErrorEnum;

@Getter
public class BaseException extends RuntimeException {
    private final String code;
    private final HttpStatus httpStatus;

    public BaseException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
        this.httpStatus = errorEnum.getHttpStatus();
    }
}

