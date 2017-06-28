package com.github.christianfranco.geomatch.entities;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import javax.annotation.concurrent.ThreadSafe;
import java.io.Serializable;

/**
 * Represents a geospatial point value, based on {@link PhoneNumberGeoLocation}.
 * <p>
 * Created by Christian Franco on 11/12/2016.
 */
@ThreadSafe
@Immutable
public interface Point extends Serializable {

    /**
     * @return The latitude position..
     */
    @Parameter
    double getLatitude();

    /**
     * @return The longitude position.
     */
    @Parameter
    double getLongitude();
}
