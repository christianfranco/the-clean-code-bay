package com.github.christianfranco.geomatch.entities;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 12/12/2016.
 */
public class LocaleTest {

    @Test
    public void newPhoneNumberGeoLocation() throws Exception {
        Locale locale = ImmutableLocale.of("PT", "Lisbon");

        assertThat(locale.getCity(), is("Lisbon"));
        assertThat(locale.getCountryCode(), is("PT"));
        assertThat(locale.asString(), is("(PT - Lisbon)"));
    }
}
