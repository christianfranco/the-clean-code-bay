package com.github.christianfranco.geomatch.exception;

import com.github.christianfranco.geomatch.entities.ErrorCode;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 19/12/2016.
 */
public class GeoLocationRepositoryExceptionTest {

    @Test
    public void test() throws Exception {
        GeoLocationRepositoryException exception = new GeoLocationRepositoryException(ErrorCode.PHONE_ERROR_00001);

        assertThat(exception.getErrorCode(), is(ErrorCode.PHONE_ERROR_00001));
        assertThat(exception.getParameters().length, is(0));
    }
}