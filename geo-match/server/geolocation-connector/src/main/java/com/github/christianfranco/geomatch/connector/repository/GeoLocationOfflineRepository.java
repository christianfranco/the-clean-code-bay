package com.github.christianfranco.geomatch.connector.repository;

import com.github.christianfranco.geomatch.connector.repository.model.WorldCity;
import com.github.christianfranco.geomatch.entities.*;
import com.github.christianfranco.geomatch.exception.GeoLocationRepositoryException;
import com.github.christianfranco.geomatch.repository.GeoLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * Created by Christian Franco on 14/12/2016.
 */
@Repository
public class GeoLocationOfflineRepository implements GeoLocationRepository {

    @Autowired
    private EntityManager entityManager;

    @Nonnull
    @Override
    public Point getGeoSpacialPoint(@Nonnull Locale locale) throws GeoLocationRepositoryException {
        checkArgument(isNotBlank(locale.getCountryCode()), ErrorCode.GEOLOCATION_00002);
        checkArgument(isNotBlank(locale.getCity()), ErrorCode.GEOLOCATION_00003);

        TypedQuery<WorldCity> namedQuery = locale.getCity().equals(PhoneNumber.TOLL_FREE)
                ? queryCountry(locale) : queryCountryCity(locale);

        return namedQuery.getResultList().stream()
                .findFirst()
                .map(worldCity -> ImmutablePoint.of(worldCity.getLatitude(), worldCity.getLongitude()))
                .orElseThrow(() -> new GeoLocationRepositoryException(ErrorCode.GEOLOCATION_00001));
    }

    private TypedQuery<WorldCity> queryCountryCity(@Nonnull Locale locale) {
        return entityManager.createNamedQuery(WorldCity.WORLD_CITY_QUERY_BY_COUNTRY_CITY, WorldCity.class)
                .setParameter("countryName", locale.getCountryCode().toLowerCase())
                .setParameter("cityName", normalise(locale.getCity()));
    }

    private TypedQuery<WorldCity> queryCountry(@Nonnull Locale locale) {
        return entityManager.createNamedQuery(WorldCity.WORLD_CITY_QUERY_BY_COUNTRY, WorldCity.class)
                .setParameter("countryName", locale.getCountryCode().toLowerCase());
    }

    private String normalise(String input) {
       return stripAccents(stripToEmpty(input))
                .replace("`", " ")
                .replace("'", " ")
                .replace("´", " ").trim().toLowerCase();
    }
}
