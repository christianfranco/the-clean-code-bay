package com.github.christianfranco.geomatch.helper;

import com.github.christianfranco.geomatch.entities.ImmutablePoint;
import com.github.christianfranco.geomatch.entities.Point;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 18/12/2016.
 */
public class DistanceCalculatorTest {
    private final static Point PT_LISBON = ImmutablePoint.of(38.716667, -9.133333);
    private final static Point PT_PORTO = ImmutablePoint.of(39.871429, -7.993068);
    private final static Point PT_SETUBAL = ImmutablePoint.of(38.533333, -8.9);
    private final static Point US_CALIFORNIA = ImmutablePoint.of(35.9041667, -75.6808333);
    private final static Point GB_SEVENOAKS = ImmutablePoint.of(51.266667, 0.2);

    @Test
    public void testShortDistance_target_lisbon_same_country() throws Exception {
        double distanceL_P = DistanceCalculator.distance(PT_LISBON, PT_PORTO);
        double distanceL_S = DistanceCalculator.distance(PT_LISBON, PT_SETUBAL);

        assertThat(distanceL_P > distanceL_S, is(true) );
    }

    @Test
    public void testShortDistance_target_lisbon_other_countries() throws Exception {
        double distanceL_US = DistanceCalculator.distance(PT_LISBON, US_CALIFORNIA);
        double distanceL_GB = DistanceCalculator.distance(PT_LISBON, GB_SEVENOAKS);

        assertThat(distanceL_US > distanceL_GB, is(true) );
    }
}