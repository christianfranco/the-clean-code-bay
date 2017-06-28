package com.github.christianfranco.geomatch.exception;

import com.github.christianfranco.geomatch.entities.ErrorCode;

/**
 * Generic exception for {@link com.github.christianfranco.geomatch.repository.GeoLocationRepository}
 * <p>
 * Created by Christian Franco on 11/12/2016.
 */
public class GeoLocationRepositoryException extends GeoMathException {

    public GeoLocationRepositoryException(ErrorCode errorCode) {
        super(errorCode);
    }
}
