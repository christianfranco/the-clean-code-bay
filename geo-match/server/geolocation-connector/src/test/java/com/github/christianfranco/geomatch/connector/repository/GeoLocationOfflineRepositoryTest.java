package com.github.christianfranco.geomatch.connector.repository;

import com.github.christianfranco.geomatch.connector.TestCase;
import com.github.christianfranco.geomatch.entities.ImmutableLocale;
import com.github.christianfranco.geomatch.entities.ImmutablePoint;
import com.github.christianfranco.geomatch.entities.PhoneNumber;
import com.github.christianfranco.geomatch.entities.Point;
import com.github.christianfranco.geomatch.exception.GeoLocationRepositoryException;
import com.github.christianfranco.geomatch.repository.GeoLocationRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Christian Franco on 14/12/2016.
 */
public class GeoLocationOfflineRepositoryTest extends TestCase {

    private GeoLocationRepository geoLocationRepository;

    @Before
    public void setUp() throws Exception {
        geoLocationRepository = getContext().getBean("geoLocationRepository", GeoLocationRepository.class);
    }

    @Test
    public void getGeoSpacialPoint() throws Exception {
        Point point = geoLocationRepository.getGeoSpacialPoint(ImmutableLocale.of("PT", "Lisbon"));
        assertThat(point, is(ImmutablePoint.of(38.716667, -9.133333)));
    }

    @Test
    public void getGeoSpacialPoint_tollFree() throws Exception {
        Point point = geoLocationRepository.getGeoSpacialPoint(ImmutableLocale.of("PT", PhoneNumber.TOLL_FREE));
        assertThat(point, is(ImmutablePoint.of(38.716667, -9.133333)));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void getGeoSpacialPoint_error_cityEmpty() throws Exception {
        geoLocationRepository.getGeoSpacialPoint(ImmutableLocale.of("PT", " "));
    }

    @Test(expected = GeoLocationRepositoryException.class)
    public void getGeoSpacialPoint_error_cityNotFound() throws Exception {
        geoLocationRepository.getGeoSpacialPoint(ImmutableLocale.of("PT", "invalid_city"));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void getGeoSpacialPoint_error_countryEmpty() throws Exception {
        geoLocationRepository.getGeoSpacialPoint(ImmutableLocale.of(" ", "Lisbon"));
    }

    @Test(expected = GeoLocationRepositoryException.class)
    public void getGeoSpacialPoint_error_countryNotFound() throws Exception {
        geoLocationRepository.getGeoSpacialPoint(ImmutableLocale.of("invalid_country ", "Lisbon"));
    }
}
