package com.github.christianfranco.geomatch.connector.repository.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 19/12/2016.
 */
public class WorldCityPKTest {
    private WorldCityPK worldCityPK1;
    private WorldCityPK worldCityPK2;

    @Before
    public void setUp() throws Exception {
        worldCityPK1 = new WorldCityPK();
        worldCityPK1.setCity("city1");
        worldCityPK1.setCountry("country1");
        worldCityPK1.setRegion("region1");

        worldCityPK2 = new WorldCityPK();
        worldCityPK2.setCity("city2");
        worldCityPK2.setCountry("country2");
        worldCityPK2.setRegion("region2");
    }

    @Test
    public void hashCodeTest() throws Exception {
        int hashCode1 = worldCityPK1.hashCode();
        int hashCode2 = worldCityPK2.hashCode();

        Assert.assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void equalsTest() throws Exception {
        assertThat(Objects.equals(worldCityPK1, worldCityPK2), is(false));
    }

    @Test
    public void equalsTest_memory() throws Exception {
        assertThat(Objects.equals(worldCityPK1, worldCityPK1), is(true));
    }

    @Test
    public void equalsTest_true() throws Exception {
        worldCityPK2.setCity("city1");
        worldCityPK2.setCountry("country1");
        worldCityPK2.setRegion("region1");

        assertThat(Objects.equals(worldCityPK1, worldCityPK2), is(true));
    }

    @Test
    public void equalsTest_diff_obj() throws Exception {
        assertThat(Objects.equals(worldCityPK1, new Object()), is(false));
    }
}