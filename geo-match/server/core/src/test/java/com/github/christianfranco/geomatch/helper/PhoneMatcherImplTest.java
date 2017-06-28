package com.github.christianfranco.geomatch.helper;

import com.github.christianfranco.geomatch.entities.*;
import com.github.christianfranco.geomatch.exception.GeoMathException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 18/12/2016.
 */
public class PhoneMatcherImplTest {

    private PhoneMatcherImpl phoneMatcher;

    private PhoneNumberGeoLocation pt_lisbon;
    private PhoneNumberGeoLocation pt_porto;
    private PhoneNumberGeoLocation pt_setubal;
    private PhoneNumberGeoLocation us;
    private PhoneNumberGeoLocation gb;

    @Before
    public void setUp() throws Exception {
        phoneMatcher = new PhoneMatcherImpl();

        pt_lisbon = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(38.716667, -9.133333),
                ImmutablePhoneNumber.of(351, 211230000L, ImmutableLocale.of("PT", "Lisbon"))
        );

        pt_porto = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(39.871429, -7.993068),
                ImmutablePhoneNumber.of(351, 222220000L, ImmutableLocale.of("PT", "Porto"))
        );

        pt_setubal = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(38.533333, -8.9),
                ImmutablePhoneNumber.of(351, 265120000L, ImmutableLocale.of("PT", "Setúbal"))
        );

        us = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(35.9041667, -75.6808333),
                ImmutablePhoneNumber.of(1, 4159690000L, ImmutableLocale.of("US", "California"))
        );

        gb = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(51.266667, 0.2),
                ImmutablePhoneNumber.of(44, 975180000L, ImmutableLocale.of("GB", "Sevenoaks"))
        );
    }

    @Test
    public void closestNumberMatch() throws Exception {
        PhoneNumberGeoLocation match = phoneMatcher.closestNumberMatch(pt_lisbon, Arrays.asList(us, gb));

        assertThat(match, is(gb));
    }

    @Test
    public void closestNumberMatchOnlySameCountry() throws Exception {
        PhoneNumberGeoLocation match = phoneMatcher.closestNumberMatchOnlySameCountry(pt_lisbon, Arrays.asList(pt_porto, pt_setubal));

        assertThat(match, is(pt_setubal));
    }

    @Test(expected = GeoMathException.class)
    public void closestNumberMatchOnlySameCountry_noMatch() throws Exception {
        phoneMatcher.closestNumberMatchOnlySameCountry(pt_lisbon, Arrays.asList(us, gb));
    }

    @Test
    public void closestNumberMatchOnlySameCountry_atLeastOneCountryMatch() throws Exception {
        PhoneNumberGeoLocation match = phoneMatcher.closestNumberMatchOnlySameCountry(pt_lisbon, Arrays.asList(us, pt_setubal));
        assertThat(match, is(pt_setubal));
    }
}