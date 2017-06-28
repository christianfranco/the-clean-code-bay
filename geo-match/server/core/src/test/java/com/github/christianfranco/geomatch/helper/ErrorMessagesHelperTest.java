package com.github.christianfranco.geomatch.helper;

import com.github.christianfranco.geomatch.entities.CallResponse;
import com.github.christianfranco.geomatch.entities.ErrorCode;
import com.github.christianfranco.geomatch.exception.GeoMathException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 18/12/2016.
 */
public class ErrorMessagesHelperTest {
    private ErrorMessagesHelper<Object> errorMessagesHelper;

    @Before
    public void setUp() throws Exception {
        errorMessagesHelper = new ErrorMessagesHelper<>(Object.class, "en");
    }

    @Test
    public void getErrorMessages() throws Exception {
        assertThat(errorMessagesHelper.getErrorMessages().isEmpty(), notNullValue());
        assertThat(errorMessagesHelper.getErrorMessages().isEmpty(), is(true));
    }

    @Test
    public void criticalErrorResponse() throws Exception {
        CallResponse callResponse = errorMessagesHelper.criticalErrorResponse(ErrorCode.GEOLOCATION_00004);

        assertThat(callResponse.getErrorMessages(), contains("No match found."));
    }

    @Test
    public void addErrorMessage() throws Exception {
        errorMessagesHelper.addErrorMessage(new GeoMathException(ErrorCode.GEOLOCATION_00004));

        assertThat(errorMessagesHelper.getErrorMessages(), contains("No match found."));
    }

    @Test
    public void testNewWithInvalidLanguage_changeToDefault_english() throws Exception {
        errorMessagesHelper = new ErrorMessagesHelper<>(Object.class, "invalid");

        // Default English
        errorMessagesHelper.addErrorMessage(new GeoMathException(ErrorCode.GEOLOCATION_00004));
        assertThat(errorMessagesHelper.getErrorMessages(), contains("No match found."));
    }

    @Test
    public void testNewWithNull_changeToDefault_english() throws Exception {
        errorMessagesHelper = new ErrorMessagesHelper<>(Object.class, null);

        // Default English
        errorMessagesHelper.addErrorMessage(new GeoMathException(ErrorCode.GEOLOCATION_00004));
        assertThat(errorMessagesHelper.getErrorMessages(), contains("No match found."));
    }

    @Test
    public void testNewWithNotSupportedLanguage_changeToDefault_english() throws Exception {
        errorMessagesHelper = new ErrorMessagesHelper<>(Object.class, "pt");

        // Default English
        errorMessagesHelper.addErrorMessage(new GeoMathException(ErrorCode.GEOLOCATION_00004));
        assertThat(errorMessagesHelper.getErrorMessages(), contains("No match found."));
    }
}