package com.github.christianfranco.geomatch.connector.validation;

import com.github.christianfranco.geomatch.connector.TestCase;
import com.github.christianfranco.geomatch.exception.InvalidPhoneNumberFormatException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 12/12/2016.
 */
public class PhoneNumberValidatorTest extends TestCase{

    private PhoneNumberValidator validation;

    @Before
    public void setUp() throws Exception {
        validation = getContext().getBean("phoneNumberValidator", PhoneNumberValidator.class);
    }

    @Test
    public void isPhoneNumberValid_pt() throws Exception {
        assertThat(validation.validate("+351218222888"), is(true));
    }

    @Test
    public void isPhoneNumberValid_us() throws Exception {
        assertThat(validation.validate("+12018840000"), is(true));
    }

    @Test(expected = InvalidPhoneNumberFormatException.class)
    public void isPhoneNumberInvalid_nullValue() throws Exception {
        validation.validate(null);
    }

    @Test(expected = InvalidPhoneNumberFormatException.class)
    public void isPhoneNumberInvalid_wrongCountryDialing() throws Exception {
        validation.validate("+000218222888");
    }

    @Test(expected = InvalidPhoneNumberFormatException.class)
    public void isPhoneNumberInvalid_wrongNationalNumber() throws Exception {
        validation.validate("+351999999999999999999");
    }

    @Test(expected = InvalidPhoneNumberFormatException.class)
    public void isPhoneNumberInvalid_wrongFormat() throws Exception {
        validation.validate("999999999999999999");
    }

    @Test(expected = InvalidPhoneNumberFormatException.class)
    public void isPhoneNumberInvalid_wrongNoCountryDialog() throws Exception {
        validation.validate("044 668 1800");
    }
}