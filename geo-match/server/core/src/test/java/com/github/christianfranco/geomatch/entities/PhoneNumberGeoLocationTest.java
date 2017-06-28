package com.github.christianfranco.geomatch.entities;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 11/12/2016.
 */
public class PhoneNumberGeoLocationTest {

    @Test
    public void newPhoneNumberGeoLocation() throws Exception {
        PhoneNumberGeoLocation geoLocation = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(38.736946, -9.142685),
                ImmutablePhoneNumber.of(351, 218333444, ImmutableLocale.of("PT", "Lisbon"))
        );

        assertThat(geoLocation.getPoint(), is(ImmutablePoint.of(38.736946, -9.142685)));
        assertThat(geoLocation.getPhoneNumber(), is(ImmutablePhoneNumber.of(351, 218333444, ImmutableLocale.of("PT", "Lisbon"))));
    }
}