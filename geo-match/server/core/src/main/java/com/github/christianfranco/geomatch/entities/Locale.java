package com.github.christianfranco.geomatch.entities;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import javax.annotation.concurrent.ThreadSafe;
import java.io.Serializable;

/**
 * Defines the Locale of a {@link PhoneNumber}
 * <p>
 * Created by Christian Franco on 12/12/2016.
 */
@ThreadSafe
@Immutable
public interface Locale extends Serializable {

    /**
     * @return The Country Code in ISO Alpha-2 format. Ex.: PT
     */
    @Parameter
    String getCountryCode();

    /**
     * @return The City name
     */
    @Parameter
    String getCity();

    /**
     * @return String format. Ex.: (US - San Francisco)
     */
    @Default
    default String asString() {
        return "(" + getCountryCode() + " - " + getCity() + ")";
    }
}
