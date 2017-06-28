package com.github.christianfranco.geomatch.entities;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import javax.annotation.concurrent.ThreadSafe;
import java.io.Serializable;

import static java.lang.String.valueOf;

/**
 * Entity that contains the Phone number data.
 * <p>
 * Created by Christian Franco on 11/12/2016.
 *
 * @see PhoneNumberGeoLocation for geo-location information.
 */
@ThreadSafe
@Immutable
public interface PhoneNumber extends Serializable {
    String TOLL_FREE = "tool-free";

    /**
     * @return The country dialing code.
     */
    @Parameter
    int getCountryNumber();

    /**
     * @return The national prefix dialing number plus the phone number.
     */
    @Parameter
    long getNationalNumber();

    /**
     * @return The locale of a {@link PhoneNumber}.
     * @see Locale
     */
    @Parameter
    Locale getLocale();

    @Default
    default String toE164Format() {
        return "+" + valueOf(getCountryNumber()) + valueOf(getNationalNumber());
    }
}
