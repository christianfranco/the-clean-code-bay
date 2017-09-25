package com.github.christianfranco.geomatch.client.helper;

import org.junit.Test;

/**
 * Created by Christian Franco on 20/12/2016.
 */
public class ArgumentCheckerTest {

    @Test(expected = Exception.class)
    public void checkInvalidArguments() throws Exception {
        ArgumentChecker.checkInvalidArguments("--same");
    }

    @Test
    public void checkInvalidArguments_all_valid() throws Exception {
        ArgumentChecker.checkInvalidArguments("--help");
        ArgumentChecker.checkInvalidArguments("--same-country-only");
    }
}