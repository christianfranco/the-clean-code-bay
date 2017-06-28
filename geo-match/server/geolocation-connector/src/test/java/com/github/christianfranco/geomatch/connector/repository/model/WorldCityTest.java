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
public class WorldCityTest {

    private WorldCity worldCity1;
    private WorldCity worldCity2;

    @Before
    public void setUp() throws Exception {
        WorldCityPK worldCityPK1 = new WorldCityPK();
        worldCityPK1.setCity("city1");
        worldCityPK1.setCountry("country1");
        worldCityPK1.setRegion("region1");

        worldCity1 = new WorldCity();
        worldCity1.setWorldCityPK(worldCityPK1);

        WorldCityPK worldCityPK2 = new WorldCityPK();
        worldCityPK2.setCity("city2");
        worldCityPK2.setCountry("country2");
        worldCityPK2.setRegion("region2");

        worldCity2 = new WorldCity();
        worldCity2.setWorldCityPK(worldCityPK2);
    }

    @Test
    public void hashCodeTest() throws Exception {
        int hashCode1 = worldCity1.hashCode();
        int hashCode2 = worldCity2.hashCode();

        Assert.assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void equalsTest() throws Exception {
        assertThat(Objects.equals(worldCity1, worldCity2), is(false));
    }

    @Test
    public void equalsTest_memory() throws Exception {
        assertThat(Objects.equals(worldCity1, worldCity1), is(true));
    }

    @Test
    public void equalsTest_true() throws Exception {
        worldCity2.getWorldCityPK().setCity("city1");
        worldCity2.getWorldCityPK().setCountry("country1");
        worldCity2.getWorldCityPK().setRegion("region1");

        assertThat(Objects.equals(worldCity1, worldCity2), is(true));
    }

    @Test
    public void equalsTest_diff_obj() throws Exception {
        assertThat(Objects.equals(worldCity1, new Object()), is(false));
    }
}