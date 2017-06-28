package com.github.christianfranco.geomatch.entities;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 17/12/2016.
 */
public class CallResponseTest {

    private String closestMatchNumber;
    private String location;

    @Before
    public void setUp() throws Exception {
        closestMatchNumber = "+351217888999";
        location = "PT";
    }

    @Test
    public void newCallResponse() throws Exception {
        CallResponse callResponse = ImmutableCallResponse.builder()
                .selectedCustomerNumber(closestMatchNumber)
                .location(location)
                .build();

        assertThat(callResponse.getSelectedCustomerNumber(), is(closestMatchNumber));
        assertThat(callResponse.getLocation(), is(location));
        assertThat(callResponse.getErrorMessages().isEmpty(), is(true));
    }

    @Test
    public void newCallResponse_withErrors() throws Exception {
        CallResponse callResponse = ImmutableCallResponse.builder()
                .selectedCustomerNumber(closestMatchNumber)
                .location(location)
                .errorMessages(ImmutableList.of("error"))
                .build();

        assertThat(callResponse.getSelectedCustomerNumber(), is(closestMatchNumber));
        assertThat(callResponse.getLocation(), is(location));
        assertThat(callResponse.getErrorMessages(), contains("error"));
    }

    @Test
    public void newCallResponse_withErrors_noNumber() throws Exception {
        CallResponse callResponse = ImmutableCallResponse.builder()
                .errorMessages(ImmutableList.of("error"))
                .build();

        assertThat(callResponse.getSelectedCustomerNumber().isEmpty(), is(true));
        assertThat(callResponse.getLocation().isEmpty(), is(true));
        assertThat(callResponse.getErrorMessages(), contains("error"));
    }
}