package com.github.christianfranco.geomatch.connector.mapper;

import com.github.christianfranco.geomatch.entities.PhoneNumber;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 12/12/2016.
 */
public class PhoneNumberMapperTest {
    private PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder;
    private PhoneNumberUtil phoneNumberUtil;

    @Before
    public void setUp() throws Exception {
        phoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance();
        phoneNumberUtil = PhoneNumberUtil.getInstance();
    }

    @Test
    public void apply() throws Exception {
        PhoneNumberMapper phoneNumberMapper = new PhoneNumberMapper(phoneNumberUtil, phoneNumberOfflineGeocoder);

        Optional<PhoneNumber> phoneNumber = phoneNumberMapper.apply("+351218999111");

        assertThat(phoneNumber.isPresent(), is(true));

        phoneNumber.ifPresent(number -> {
            assertThat(number.getCountryNumber(), is(351));
            assertThat(number.getNationalNumber(), is(218999111L));
        });
    }

    @Test
    public void apply_normalise() throws Exception {
        PhoneNumberMapper phoneNumberMapper = new PhoneNumberMapper(phoneNumberUtil, phoneNumberOfflineGeocoder);

        Optional<PhoneNumber> phoneNumber = phoneNumberMapper.apply("+15148710000");

        assertThat(phoneNumber.isPresent(), is(true));

        phoneNumber.ifPresent(number -> {
            assertThat(number.getCountryNumber(), is(1));
            assertThat(number.getNationalNumber(), is(5148710000L));
        });
    }

    @Test
    public void apply_error() throws Exception {
        PhoneNumberMapper phoneNumberMapper = new PhoneNumberMapper(phoneNumberUtil, phoneNumberOfflineGeocoder);

        Optional<PhoneNumber> phoneNumber = phoneNumberMapper.apply("8999111");

        assertThat(phoneNumber.isPresent(), is(false));
    }
}