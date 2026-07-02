package org.urusso.vgcteamwars.common.exception;


import org.urusso.vgcteamwars.common.constants.ErrorEnum;

public class BusinessException extends BaseException {
    public BusinessException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
