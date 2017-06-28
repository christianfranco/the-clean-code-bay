package com.github.christianfranco.geomatch.helper;

import com.github.christianfranco.geomatch.exception.GeoMathException;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Created by Christian Franco on 17/12/2016.
 */
public interface PhoneMatcher<E> {

    @Nonnull
    E closestNumberMatch(@Nonnull final E destinationNumber, @Nonnull final Collection<E> customerNumbers) throws GeoMathException;

    @Nonnull
    E closestNumberMatchOnlySameCountry(@Nonnull final E destinationNumber, @Nonnull final Collection<E> customerNumbers) throws GeoMathException;
}
