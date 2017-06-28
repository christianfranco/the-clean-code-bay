package com.github.christianfranco.geomatch.connector.mapper;

import com.github.christianfranco.geomatch.connector.validation.PhoneNumberValidator;
import com.github.christianfranco.geomatch.entities.ImmutableLocale;
import com.github.christianfranco.geomatch.entities.ImmutablePhoneNumber;
import com.github.christianfranco.geomatch.entities.Locale;
import com.github.christianfranco.geomatch.entities.PhoneNumber;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Function;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.split;

/**
 * Created by Christian Franco on 12/12/2016.
 */
public class PhoneNumberMapper implements Function<String, Optional<PhoneNumber>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumberValidator.class);

    private static final String DEFAULT_REGION = "ZZ";

    private final PhoneNumberUtil phoneNumberUtil;
    private final PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder;

    public PhoneNumberMapper(@Nonnull final PhoneNumberUtil phoneNumberUtil, @Nonnull final PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder) {
        this.phoneNumberUtil = phoneNumberUtil;
        this.phoneNumberOfflineGeocoder = phoneNumberOfflineGeocoder;
    }

    /**
     * Transform a string ISO ALPHA 2 format to a {@link PhoneNumber}
     *
     * @param phoneNumber A valid phone number in ISO ALPHA 2 format.
     * @return A valid {@link PhoneNumber}.
     */
    @Override
    public Optional<PhoneNumber> apply(@Nonnull final String phoneNumber) {
        final Optional<Phonenumber.PhoneNumber> phoneNumberParsed = parsePhoneNumber(phoneNumber);

        if (phoneNumberParsed.isPresent()) {
            return Optional.of(ImmutablePhoneNumber.of(
                    phoneNumberParsed.get().getCountryCode(),
                    phoneNumberParsed.get().getNationalNumber(),
                    loadLocale(phoneNumberParsed.get())));
        }

        return Optional.empty();
    }

    public Optional<Phonenumber.PhoneNumber> parsePhoneNumber(String phoneNumber) {
        try {
            return Optional.ofNullable(phoneNumberUtil.parse(phoneNumber, DEFAULT_REGION));
        } catch (Exception e) {
            LOGGER.error("Error to parse the phone number: " + phoneNumber, e);
        }
        return Optional.empty();
    }

    private Locale loadLocale(Phonenumber.PhoneNumber phoneNumber) {
        String city = phoneNumberOfflineGeocoder.getDescriptionForNumber(phoneNumber, java.util.Locale.US);
        String countryCode = phoneNumberUtil.getRegionCodeForNumber(phoneNumber);
        city = isBlank(city) ? PhoneNumber.TOLL_FREE : city;

        return ImmutableLocale.of(countryCode, normalize(city));
    }

    private String normalize(String input) {
        if (input.contains(",")) {
            return split(input, ",")[0];
        }
        return input;
    }
}
