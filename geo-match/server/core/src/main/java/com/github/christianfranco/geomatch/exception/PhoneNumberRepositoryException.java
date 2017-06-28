package com.github.christianfranco.geomatch.exception;

import com.github.christianfranco.geomatch.entities.ErrorCode;

/**
 * Created by Christian Franco on 12/12/2016 14:03.
 */
public class PhoneNumberRepositoryException extends GeoMathException {
    public PhoneNumberRepositoryException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PhoneNumberRepositoryException(ErrorCode errorCode, String... parameters) {
        super(errorCode, parameters);
    }
}
