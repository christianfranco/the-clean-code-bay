package com.github.christianfranco.geomatch.repository;

import com.github.christianfranco.geomatch.entities.PhoneNumber;
import com.github.christianfranco.geomatch.exception.InvalidPhoneNumberFormatException;

import javax.annotation.Nonnull;

/**
 * Repository that contains the Phone number data.
 * <p>
 * Created by Christian Franco on 12/12/2016.
 */
public interface PhoneNumberRepository {

    /**
     * Loads a Phone Number data.
     *
     * @param number The Phone Number in a E164 format. Ex. +351218777888
     * @return A valid {@link PhoneNumber}
     */
    @Nonnull
    PhoneNumber getPhoneNumber(@Nonnull final String number) throws InvalidPhoneNumberFormatException;
}
