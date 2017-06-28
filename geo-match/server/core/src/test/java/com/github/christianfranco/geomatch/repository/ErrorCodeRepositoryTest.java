package com.github.christianfranco.geomatch.repository;

import com.github.christianfranco.geomatch.entities.ErrorCode;
import com.github.christianfranco.geomatch.exception.GeoMathException;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Christian Franco on 12/12/2016.
 */
public class ErrorCodeRepositoryTest {

    private ErrorCodeRepository errorCodeRepository;

    @Before
    public void setUp() throws Exception {
        errorCodeRepository = new ErrorCodeRepository(Locale.getDefault());
    }

    @Test
    public void findErrorMessage() throws Exception {
        assertThat(errorCodeRepository.findErrorMessage(new GeoMathException(ErrorCode.PHONE_ERROR_00001)),
                is("The phone number cannot be null or empty."));
    }

    @Test
    public void findFormattedErrorMessage() throws Exception {
        assertThat(errorCodeRepository.findErrorMessage(new GeoMathException(ErrorCode.PHONE_ERROR_00002, "INVALID_NUMBER")),
                is("The phone number (INVALID_NUMBER) has a wrong format."));
    }

    @Test
    public void verifyIfSomeErrorMessageIsMissing() throws Exception {
        Stream.of(ErrorCode.values())
                .forEach(errorCode -> {
                    try {
                        errorCodeRepository.findErrorMessage(errorCode);
                    } catch (Exception e) {
                        fail("There is a error code missing in property file: " + errorCode.name());
                    }
                });
    }
}