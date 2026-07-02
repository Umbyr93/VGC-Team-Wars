package org.urusso.vgcteamwars.common.exception;

import org.urusso.vgcteamwars.common.constants.ErrorEnum;

public class AuthorizationException extends BaseException {
    public AuthorizationException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
