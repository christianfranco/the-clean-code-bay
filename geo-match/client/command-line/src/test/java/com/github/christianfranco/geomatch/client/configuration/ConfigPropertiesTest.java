package com.github.christianfranco.geomatch.client.configuration;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 20/12/2016.
 */
public class ConfigPropertiesTest {
    @Test
    public void getGeoMatchEndPoint() throws Exception {
       String endpoint =  ConfigProperties.getGeoMatchEndPoint();

       assertThat(endpoint, is("http://localhost:8080/geomatch"));
    }
}