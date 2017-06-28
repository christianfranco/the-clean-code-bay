package com.github.christianfranco.geomatch.helper;

import com.github.christianfranco.geomatch.entities.ErrorCode;
import com.github.christianfranco.geomatch.entities.PhoneNumberGeoLocation;
import com.github.christianfranco.geomatch.exception.GeoMathException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Created by Christian Franco on 18/12/2016.
 */
public class PhoneMatcherImpl implements PhoneMatcher<PhoneNumberGeoLocation> {

    @Nonnull
    @Override
    public PhoneNumberGeoLocation closestNumberMatch(@Nonnull final PhoneNumberGeoLocation targetNumber,
                                                     @Nonnull final Collection<PhoneNumberGeoLocation> customerNumbers) throws GeoMathException {
        return getMinDistanceBetweenPhoneNumbers(targetNumber, customerNumbers);
    }

    @Nonnull
    @Override
    public PhoneNumberGeoLocation closestNumberMatchOnlySameCountry(@Nonnull final PhoneNumberGeoLocation targetNumber,
                                                                    @Nonnull final Collection<PhoneNumberGeoLocation> customerNumbers) throws GeoMathException {

        Collection<PhoneNumberGeoLocation> onlySameCountry = filterOnlySameCountry(targetNumber, customerNumbers);
        if (onlySameCountry.isEmpty()) {
            throw new GeoMathException(ErrorCode.GEOLOCATION_00004);
        }

        return getMinDistanceBetweenPhoneNumbers(targetNumber, onlySameCountry);
    }

    private PhoneNumberGeoLocation getMinDistanceBetweenPhoneNumbers(@Nonnull final PhoneNumberGeoLocation targetNumber,
                                                                     @Nonnull final Collection<PhoneNumberGeoLocation> customerNumbers) throws GeoMathException {
        return customerNumbers.stream()
                .map(customerNumber -> ImmutablePair.of(customerNumber, DistanceCalculator.distance(targetNumber.getPoint(), customerNumber.getPoint())))
                .min(Comparator.comparingDouble(Pair::getRight))
                .map(Pair::getLeft)
                .orElseThrow(() -> new GeoMathException(ErrorCode.GEOLOCATION_00004));
    }

    private Collection<PhoneNumberGeoLocation> filterOnlySameCountry(@Nonnull final PhoneNumberGeoLocation targetNumber,
                                                                     @Nonnull final Collection<PhoneNumberGeoLocation> customerNumbers) {
        return customerNumbers.stream()
                .filter(customerNumber -> targetNumber.getPhoneNumber().getLocale().getCountryCode()
                      .equalsIgnoreCase(customerNumber.getPhoneNumber().getLocale().getCountryCode()))
                .collect(Collectors.toSet());
    }
}
