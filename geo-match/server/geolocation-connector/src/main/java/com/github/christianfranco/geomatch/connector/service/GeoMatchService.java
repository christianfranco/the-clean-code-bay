package com.github.christianfranco.geomatch.connector.service;

import com.github.christianfranco.geomatch.command.phonenumber.GetClosestGeographicMatchCommand;
import com.github.christianfranco.geomatch.connector.mapper.CallValueObject;
import com.github.christianfranco.geomatch.entities.ImmutableCall;
import com.github.christianfranco.geomatch.entities.ImmutableCallResponse;
import com.github.christianfranco.geomatch.helper.PhoneMatcherImpl;
import com.github.christianfranco.geomatch.repository.GeoLocationRepository;
import com.github.christianfranco.geomatch.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

import static com.github.christianfranco.geomatch.entities.ImmutableCallResponse.copyOf;

/**
 * Created by Christian Franco on 18/12/2016.
 */
@Service
public class GeoMatchService {

    @Autowired
    private GeoLocationRepository geoLocationRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public ImmutableCallResponse getClosestGeographicMatch(@Nonnull final CallValueObject call) {
        GetClosestGeographicMatchCommand command = new GetClosestGeographicMatchCommand(
                geoLocationRepository, phoneNumberRepository, new PhoneMatcherImpl());

        return copyOf(command.call(ImmutableCall.copyOf(call)));
    }
}
