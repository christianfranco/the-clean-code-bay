package com.github.christianfranco.geomatch.repository;

import com.github.christianfranco.geomatch.entities.ImmutableLocale;
import com.github.christianfranco.geomatch.entities.ImmutablePoint;
import com.github.christianfranco.geomatch.entities.Locale;
import com.github.christianfranco.geomatch.entities.Point;
import com.github.christianfranco.geomatch.exception.GeoLocationRepositoryException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Christian Franco on 11/12/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class GeoLocationRepositoryTest {
    @Mock
    private GeoLocationRepository geoLocationRepository;

    private Point point;
    private Locale validLocation;
    private Locale invalidCity;
    private Locale invalidCountry;

    @Before
    public void setUp() throws Exception {
        point = ImmutablePoint.of(38.736946, -9.142685);

        validLocation = ImmutableLocale.of("PT", "Lisbon");
        invalidCity = ImmutableLocale.of("PT", "invalid_city");
        invalidCountry = ImmutableLocale.of("invalid_country", "Lisbon");
    }

    @Test
    public void getGeoSpacialPoint() throws Exception {
        when(geoLocationRepository.getGeoSpacialPoint(validLocation)).thenReturn(point);

        Point geoSpacialPoint = geoLocationRepository.getGeoSpacialPoint(validLocation);
        assertThat(geoSpacialPoint, is(point));
    }

    @Test(expected = GeoLocationRepositoryException.class)
    public void getGeoSpacialPoint_genericError() throws Exception {
        doThrow(GeoLocationRepositoryException.class).when(geoLocationRepository).getGeoSpacialPoint(validLocation);

        geoLocationRepository.getGeoSpacialPoint(validLocation);
    }

    @Test(expected = GeoLocationRepositoryException.class)
    public void getGeoSpacialPoint_invalidCity() throws Exception {
        doThrow(GeoLocationRepositoryException.class).when(geoLocationRepository).getGeoSpacialPoint(invalidCity);

        geoLocationRepository.getGeoSpacialPoint(invalidCity);
    }

    @Test(expected = GeoLocationRepositoryException.class)
    public void getGeoSpacialPoint_invalidCountry() throws Exception {
        doThrow(GeoLocationRepositoryException.class).when(geoLocationRepository).getGeoSpacialPoint(invalidCountry);

        geoLocationRepository.getGeoSpacialPoint(invalidCountry);
    }
}