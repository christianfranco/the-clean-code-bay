package com.github.christianfranco.geomatch.connector.repository;

import com.github.christianfranco.geomatch.connector.mapper.PhoneNumberMapper;
import com.github.christianfranco.geomatch.connector.validation.PhoneNumberValidator;
import com.github.christianfranco.geomatch.entities.ErrorCode;
import com.github.christianfranco.geomatch.entities.PhoneNumber;
import com.github.christianfranco.geomatch.exception.InvalidPhoneNumberFormatException;
import com.github.christianfranco.geomatch.repository.PhoneNumberRepository;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;

/**
 * Created by Christian Franco on 12/12/2016.
 */
@Repository
public class PhoneNumberRepositoryImpl implements PhoneNumberRepository {

    private final PhoneNumberUtil phoneNumberUtil;
    private final PhoneNumberOfflineGeocoder phoneNumberGeocoder;

    private final PhoneNumberValidator phoneNumberValidator;

    public PhoneNumberRepositoryImpl(@Nonnull final PhoneNumberUtil phoneNumberUtil,
                                     @Nonnull final PhoneNumberOfflineGeocoder phoneNumberGeocoder) {
        this.phoneNumberUtil = phoneNumberUtil;
        this.phoneNumberValidator = new PhoneNumberValidator(phoneNumberUtil, phoneNumberGeocoder);
        this.phoneNumberGeocoder = phoneNumberGeocoder;
    }

    @Nonnull
    @Override
    public PhoneNumber getPhoneNumber(@Nonnull final String phoneNumber) throws InvalidPhoneNumberFormatException {
        phoneNumberValidator.validate(phoneNumber);

        return new PhoneNumberMapper(phoneNumberUtil, phoneNumberGeocoder).apply(phoneNumber)
                .orElseThrow(() -> new InvalidPhoneNumberFormatException(ErrorCode.PHONE_ERROR_00002, phoneNumber));
    }
}
