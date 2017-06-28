package com.github.christianfranco.geomatch.entities;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 11/12/2016.
 */
public class PointTest {

    @Test
    public void newPoint() throws Exception {
        Point point = ImmutablePoint.of(38.736946, -9.142685);

        assertThat(point.getLatitude(), is(38.736946));
        assertThat(point.getLongitude(), is(-9.142685));
    }
}