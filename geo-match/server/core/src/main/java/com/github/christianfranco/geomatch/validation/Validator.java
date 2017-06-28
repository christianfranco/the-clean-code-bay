package com.github.christianfranco.geomatch.validation;

import com.github.christianfranco.geomatch.entities.ErrorCode;
import com.github.christianfranco.geomatch.exception.GeoMathException;

import javax.annotation.Nullable;

/**
 * Generic validator.
 * <p>
 *
 * @see ErrorCode
 * Created by Christian Franco on 12/12/2016.
 */
@FunctionalInterface
public interface Validator<E> {

    /**
     * Verify if a element is valid.
     *
     * @param element The element to be verified.
     * @return true if the element is valid.
     * @throws GeoMathException if the element is invalid.
     */
    boolean validate(@Nullable final E element) throws GeoMathException;
}
