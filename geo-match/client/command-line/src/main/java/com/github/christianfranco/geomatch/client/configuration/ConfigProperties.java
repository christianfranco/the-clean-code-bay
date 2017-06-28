package com.github.christianfranco.geomatch.client.configuration;

import java.util.ResourceBundle;

/**
 * Created by Christian Franco on 19/12/2016.
 */
public class ConfigProperties {
    private static final ResourceBundle PROPERTIES =  ResourceBundle.getBundle("configuration");

    private ConfigProperties() {
    }

    public static String getGeoMatchEndPoint() {
        return PROPERTIES.getString("geomatch.endpoint.address");
    }
}
