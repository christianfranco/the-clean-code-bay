package com.github.christianfranco.geomatch.connector.repository;

import com.github.christianfranco.geomatch.connector.TestCase;
import com.github.christianfranco.geomatch.entities.ImmutableLocale;
import com.github.christianfranco.geomatch.entities.ImmutablePhoneNumber;
import com.github.christianfranco.geomatch.entities.PhoneNumber;
import com.github.christianfranco.geomatch.exception.InvalidPhoneNumberFormatException;
import com.github.christianfranco.geomatch.repository.PhoneNumberRepository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 12/12/2016.
 */
public class PhoneNumberRepositoryImplTest extends TestCase{

    private PhoneNumberRepository phoneNumberRepository;
    private PhoneNumber lisbon;

    @Before
    public void setUp() throws Exception {
        phoneNumberRepository = getContext().getBean("phoneNumberRepository", PhoneNumberRepository.class);
        lisbon = ImmutablePhoneNumber.of(351, 218666555L, ImmutableLocale.of("PT", "Lisbon"));
    }

    @Test
    public void getPhoneNumber() throws Exception {
        PhoneNumber phoneNumber = phoneNumberRepository.getPhoneNumber("+351218666555");

        assertThat(phoneNumber, is(this.lisbon));
    }

    @Test
    public void getPhoneNumber_tollFree() throws Exception {
        PhoneNumber tollFree = ImmutablePhoneNumber.of(44, 8008080000L, ImmutableLocale.of("GB", PhoneNumber.TOLL_FREE));

        PhoneNumber phoneNumber = phoneNumberRepository.getPhoneNumber(tollFree.toE164Format());

        assertThat(phoneNumber, is(tollFree));
    }

    @Test(expected = InvalidPhoneNumberFormatException.class)
    public void getPhoneNumber_invalidFormat() throws Exception {
        phoneNumberRepository.getPhoneNumber("+351218666");
    }

    @Test(expected = InvalidPhoneNumberFormatException.class)
    public void getPhoneNumber_invalidFormat_noCountryDialing() throws Exception {
        phoneNumberRepository.getPhoneNumber("218666555");
    }

}