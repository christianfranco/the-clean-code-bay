package com.github.christianfranco.geomatch.connector.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.christianfranco.geomatch.connector.Application;
import com.github.christianfranco.geomatch.connector.mapper.CallValueObject;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.String.valueOf;
import static java.text.MessageFormat.format;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 18/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GeoMatchRestControllerIT {
    private static final String BASE_URI = "http://localhost:{0}/geomatch";
    private static final String APPLICATION_JSON = "application/json";
    private static final String DEFAULT_LANGUAGE = "en";

    @LocalServerPort
    private int randomServerPort;

    @Test
    public void testGeoMatchEndPoint() throws Exception {
        Client client = Client.create();

        ObjectMapper objectMapper = new ObjectMapper();
        CallValueObject callValueObject = new CallValueObject()
                .setCallToSameCountryOnly(false)
                .setDestinationNumber("+351265120000")
                .setCustomerNumbers(Arrays.asList("+351222220000", "+351211230000"))
                .setUserLanguage(DEFAULT_LANGUAGE);

        String json = objectMapper.writeValueAsString(callValueObject);

        WebResource webResource = client.resource(format(BASE_URI, valueOf(randomServerPort)));
        ClientResponse response = webResource
                .accept(APPLICATION_JSON )
                .type(APPLICATION_JSON)
                .post(ClientResponse.class, json);

        String theString = CharStreams.toString(new InputStreamReader(response.getEntityInputStream(), Charsets.UTF_8));

        assertThat(theString, containsString("+351211230000"));
        assertThat(theString, containsString("Lisbon"));
        assertThat(theString, containsString("PT"));

        System.out.print(response);
    }

    @Test
    public void testGeoMatchEndPoint_match_same_country() throws Exception {
        Client client = Client.create();

        ObjectMapper objectMapper = new ObjectMapper();
        CallValueObject callValueObject = new CallValueObject()
                .setCallToSameCountryOnly(true)
                .setDestinationNumber("+12018840000")
                .setCustomerNumbers(Arrays.asList("+15148710000", "+14159690000"))
                .setUserLanguage(DEFAULT_LANGUAGE);

        String json = objectMapper.writeValueAsString(callValueObject);

        WebResource webResource = client.resource(format(BASE_URI, valueOf(randomServerPort)));
        ClientResponse response = webResource
                .accept(APPLICATION_JSON )
                .type(APPLICATION_JSON)
                .post(ClientResponse.class, json);

        String theString = CharStreams.toString(new InputStreamReader(response.getEntityInputStream(), Charsets.UTF_8));

        assertThat(theString, containsString("+14159690000"));
        assertThat(theString, containsString("California"));
        assertThat(theString, containsString("US"));

        System.out.print(response);
    }
}