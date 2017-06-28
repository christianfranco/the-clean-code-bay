package com.github.christianfranco.geomatch.connector.service;

import com.github.christianfranco.geomatch.connector.mapper.CallValueObject;
import com.github.christianfranco.geomatch.entities.ImmutableCallResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Christian Franco on 18/12/2016.
 */
@RestController()
public class GeoMatchRestController {

    @Autowired
    private GeoMatchService geoMatchService;

    @RequestMapping(value = "/geomatch", method = RequestMethod.POST)
    public ImmutableCallResponse getClosestGeographicMatch(@RequestBody CallValueObject call) {

        return geoMatchService.getClosestGeographicMatch(call);
    }
}
