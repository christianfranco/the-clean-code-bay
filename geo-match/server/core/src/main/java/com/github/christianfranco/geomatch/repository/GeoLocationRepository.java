package com.github.christianfranco.geomatch.repository;

import com.github.christianfranco.geomatch.entities.Locale;
import com.github.christianfranco.geomatch.entities.Point;
import com.github.christianfranco.geomatch.exception.GeoLocationRepositoryException;

import javax.annotation.Nonnull;

/**
 * Repository that contains the geospacial data.
 * <p>
 * Created by Christian Franco on 11/12/2016.
 */
public interface GeoLocationRepository {

    /**
     * Load a {@link Point} based on the {@link com.github.christianfranco.geomatch.entities.PhoneNumber} locale..
     *
     * @param locale The geo-locale of the {@link com.github.christianfranco.geomatch.entities.PhoneNumber}
     * @return The geospacial point.
     * @throws GeoLocationRepositoryException When a generic error occurs}
     */
    @Nonnull
    Point getGeoSpacialPoint(@Nonnull final Locale locale) throws GeoLocationRepositoryException;
}
