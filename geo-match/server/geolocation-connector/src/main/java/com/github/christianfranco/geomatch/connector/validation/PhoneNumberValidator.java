package com.github.christianfranco.geomatch.connector.validation;

import com.github.christianfranco.geomatch.connector.mapper.PhoneNumberMapper;
import com.github.christianfranco.geomatch.entities.ErrorCode;
import com.github.christianfranco.geomatch.exception.InvalidPhoneNumberFormatException;
import com.github.christianfranco.geomatch.validation.Validator;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Created by Christian Franco on 12/12/2016.
 */
public class PhoneNumberValidator implements Validator<String> {

    private final PhoneNumberUtil phoneNumberUtil;
    private final PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder;

    public PhoneNumberValidator(@Nonnull final PhoneNumberUtil phoneNumberUtil, @Nonnull final PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder) {
        this.phoneNumberUtil = phoneNumberUtil;
        this.phoneNumberOfflineGeocoder = phoneNumberOfflineGeocoder;
    }

    @Override
    public boolean validate(@Nullable final String phoneNumber) throws InvalidPhoneNumberFormatException {

        Optional.ofNullable(phoneNumber)
                .filter(StringUtils::isNoneBlank)
                .orElseThrow(() -> new InvalidPhoneNumberFormatException(ErrorCode.PHONE_ERROR_00001));

        Optional.ofNullable(phoneNumber)
                .map(number -> new PhoneNumberMapper(phoneNumberUtil, phoneNumberOfflineGeocoder).parsePhoneNumber(number))
                .filter(Optional::isPresent)
                .filter(number -> phoneNumberUtil.isValidNumber(number.get()))
                .orElseThrow(() -> new InvalidPhoneNumberFormatException(ErrorCode.PHONE_ERROR_00002, phoneNumber));

        return true;
    }
}
