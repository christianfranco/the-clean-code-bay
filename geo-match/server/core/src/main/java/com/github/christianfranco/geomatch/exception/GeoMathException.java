package com.github.christianfranco.geomatch.exception;

import com.github.christianfranco.geomatch.entities.ErrorCode;

/**
 * Generic exception for GeoMath modules.
 *
 * Created by Christian Franco on 11/12/2016.
 */
public class GeoMathException extends Exception {
    private final ErrorCode errorCode;
    private String[] parameters;

    public GeoMathException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public GeoMathException(ErrorCode errorCode, String... parameters) {
        this.errorCode = errorCode;
        this.parameters = parameters;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Object[] getParameters() {
        return null == parameters ? new Object[0] : parameters;
    }
}
