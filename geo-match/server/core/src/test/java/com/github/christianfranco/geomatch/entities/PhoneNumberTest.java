package com.github.christianfranco.geomatch.entities;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 11/12/2016.
 */
public class PhoneNumberTest {

    @Test
    public void newPhoneNumber() throws Exception {
        PhoneNumber phoneNumber = ImmutablePhoneNumber.of(351, 212333444L, ImmutableLocale.of("PT", "Lisbon"));

        assertThat(phoneNumber.getCountryNumber(), is(351));
        assertThat(phoneNumber.getNationalNumber(), is(212333444L));
        assertThat(phoneNumber.toE164Format(), is("+351212333444"));
        assertThat(phoneNumber.getLocale(), is(ImmutableLocale.of("PT", "Lisbon")));
    }

    @Test
    public void newPhoneNumber_tollFree() throws Exception {
        PhoneNumber phoneNumber = ImmutablePhoneNumber.of(351, 212333444L, ImmutableLocale.of("PT", PhoneNumber.TOLL_FREE));

        assertThat(phoneNumber.getCountryNumber(), is(351));
        assertThat(phoneNumber.getNationalNumber(), is(212333444L));
        assertThat(phoneNumber.toE164Format(), is("+351212333444"));
        assertThat(phoneNumber.getLocale(), is(ImmutableLocale.of("PT", PhoneNumber.TOLL_FREE)));
    }
}