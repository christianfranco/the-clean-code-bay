package com.github.christianfranco.geomatch.entities;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import javax.annotation.concurrent.ThreadSafe;
import java.io.Serializable;

/**
 * Entity class that contains the {@link PhoneNumber} combined with the geo-location data.
 * <p>
 * Created by Christian Franco on 11/12/2016.
 */
@ThreadSafe
@Immutable
public interface PhoneNumberGeoLocation extends Serializable {
    /**
     * @return The geo-spatial position, based on the country/City.
     * @see Point
     */
    @Parameter
    Point getPoint();

    /**
     * @return The PhoneNumber entity.
     * @see PhoneNumber
     */
    @Parameter
    PhoneNumber getPhoneNumber();
}
