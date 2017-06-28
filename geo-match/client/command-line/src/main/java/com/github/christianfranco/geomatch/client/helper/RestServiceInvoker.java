package com.github.christianfranco.geomatch.client.helper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import javax.annotation.Nonnull;

/**
 * Created by Christian Franco on 19/12/2016.
 */
public class RestServiceInvoker<JSON_IN, JSON_OUT> {

    private static final String APPLICATION_JSON = "application/json";
    private final String endPoint;
    private final Class<JSON_OUT> jsonOutClazz;
    private Client client;

    public RestServiceInvoker(@Nonnull final String endPoint,
                              @Nonnull final Class<JSON_OUT> jsonOutClazz) {
        this.endPoint = endPoint;
        this.jsonOutClazz = jsonOutClazz;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(clientConfig);
    }

    public JSON_OUT invoke(JSON_IN responseJson) {
        WebResource webResourcePost = client.resource(endPoint);
        ClientResponse response = webResourcePost.type(APPLICATION_JSON).post(ClientResponse.class, responseJson);
        return response.getEntity(jsonOutClazz);
    }
}
