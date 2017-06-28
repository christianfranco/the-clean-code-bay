package com.github.christianfranco.geomatch.entities;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 17/12/2016.
 */
public class CallTest {

    @Test
    public void newCall() throws Exception {
        Call call = ImmutableCall.of("call_number", ImmutableList.of("number_1", "number_2"));

        assertThat(call.getCustomerNumbers(), contains("number_1", "number_2"));
        assertThat(call.getDestinationNumber(), is("call_number"));
        assertThat(call.getUserLanguage(), is("en"));
        assertThat(call.isCallToSameCountryOnly(), is(false));
    }

    @Test
    public void newCall_sameCountryOnly() throws Exception {
        Call call = ImmutableCall.of("call_number", ImmutableList.of("number_1", "number_2"))
                .withIsCallToSameCountryOnly(true);

        assertThat(call.getCustomerNumbers(), contains("number_1", "number_2"));
        assertThat(call.getDestinationNumber(), is("call_number"));
        assertThat(call.isCallToSameCountryOnly(), is(true));
    }
}