package com.github.christianfranco.geomatch.exception;

import com.github.christianfranco.geomatch.entities.ErrorCode;

/**
 * Created by Christian Franco on 12/12/2016.
 */
public class InvalidPhoneNumberFormatException extends PhoneNumberRepositoryException {

    public InvalidPhoneNumberFormatException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidPhoneNumberFormatException(ErrorCode errorCode, String... parameters) {
        super(errorCode, parameters);
    }
}
