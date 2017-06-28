package com.github.christianfranco.geomatch.repository;

import com.github.christianfranco.geomatch.entities.ImmutableLocale;
import com.github.christianfranco.geomatch.entities.ImmutablePhoneNumber;
import com.github.christianfranco.geomatch.entities.PhoneNumber;
import com.github.christianfranco.geomatch.exception.InvalidPhoneNumberFormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Christian Franco on 12/12/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class PhoneNumberRepositoryTest {

    @Mock private PhoneNumberRepository phoneNumberRepository;

    private PhoneNumber phoneNumber;

    @Before
    public void setUp() throws Exception {
        phoneNumber = ImmutablePhoneNumber.of(351, 218666555L, ImmutableLocale.of("PT", "Lisbon"));
    }

    @Test
    public void getPhoneNumber() throws Exception {
        when(phoneNumberRepository.getPhoneNumber("+351218666555")).thenReturn(phoneNumber);

        PhoneNumber phoneNumber = phoneNumberRepository.getPhoneNumber("+351218666555");

        assertThat(phoneNumber, is(this.phoneNumber));
    }

    @Test(expected = InvalidPhoneNumberFormatException.class)
    public void getPhoneNumber_invalidFormat() throws Exception {
        doThrow(InvalidPhoneNumberFormatException.class).when(phoneNumberRepository).getPhoneNumber("+351218666");

        phoneNumberRepository.getPhoneNumber("+351218666");
    }

    @Test(expected = InvalidPhoneNumberFormatException.class)
    public void getPhoneNumber_invalidFormat_noCountryDialing() throws Exception {
        doThrow(InvalidPhoneNumberFormatException.class).when(phoneNumberRepository).getPhoneNumber("218666555");

        phoneNumberRepository.getPhoneNumber("218666555");
    }
}
