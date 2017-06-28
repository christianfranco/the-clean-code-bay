package com.github.christianfranco.geomatch.command.phonenumber;

import com.github.christianfranco.geomatch.command.Command;
import com.github.christianfranco.geomatch.entities.*;
import com.github.christianfranco.geomatch.exception.GeoMathException;
import com.github.christianfranco.geomatch.helper.ErrorMessagesHelper;
import com.github.christianfranco.geomatch.helper.PhoneMatcher;
import com.github.christianfranco.geomatch.repository.GeoLocationRepository;
import com.github.christianfranco.geomatch.repository.PhoneNumberRepository;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Christian Franco on 16/12/2016.
 */
public class GetClosestGeographicMatchCommand implements Command<Call, CallResponse> {

    private final GeoLocationRepository geoLocationRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final PhoneMatcher<PhoneNumberGeoLocation> phoneMatcher;

    private ErrorMessagesHelper<GetClosestGeographicMatchCommand> errorMessagesHelper;

    public GetClosestGeographicMatchCommand(@Nonnull final GeoLocationRepository geoLocationRepository,
                                            @Nonnull final PhoneNumberRepository phoneNumberRepository,
                                            @Nonnull final PhoneMatcher<PhoneNumberGeoLocation> phoneMatcher) {
        this.geoLocationRepository = geoLocationRepository;
        this.phoneNumberRepository = phoneNumberRepository;
        this.phoneMatcher = phoneMatcher;
    }

    @Override
    public CallResponse call(@Nonnull final Call call) {
        errorMessageHelperLazyInitialization(call);

        final Optional<PhoneNumberGeoLocation> destinationNumber = loadPhoneNumberGeoLocation(call.getDestinationNumber());
        if (!destinationNumber.isPresent()) {
            return errorMessagesHelper.criticalErrorResponse(ErrorCode.PHONE_ERROR_00003);
        }

        final Collection<PhoneNumberGeoLocation> customerNumbers = loadCustomerNumbers(call);
        if (customerNumbers.isEmpty()) {
            return errorMessagesHelper.criticalErrorResponse(ErrorCode.PHONE_ERROR_00004);
        }

        final Optional<PhoneNumberGeoLocation> phoneNumberGeoLocation = selectClosestGeographicMatch(call, destinationNumber.get(), customerNumbers);
        if (!phoneNumberGeoLocation.isPresent()) {
            return errorMessagesHelper.criticalErrorResponse(ErrorCode.GEOLOCATION_00004);
        }

        return ImmutableCallResponse.builder()
                .location(phoneNumberGeoLocation.get().getPhoneNumber().getLocale().asString())
                .selectedCustomerNumber(phoneNumberGeoLocation.get().getPhoneNumber().toE164Format())
                .errorMessages(errorMessagesHelper.getErrorMessages()).build();
    }

    private Optional<PhoneNumberGeoLocation> loadPhoneNumberGeoLocation(@Nonnull final String destinationNumber) {
        try {
            PhoneNumber phoneNumber = phoneNumberRepository.getPhoneNumber(destinationNumber);
            Point geoSpacialPoint = geoLocationRepository.getGeoSpacialPoint(phoneNumber.getLocale());

            return Optional.of(ImmutablePhoneNumberGeoLocation.of(geoSpacialPoint, phoneNumber));
        } catch (final GeoMathException e) {
            errorMessagesHelper.addErrorMessage(e);
        }

        return Optional.empty();
    }

    private Collection<PhoneNumberGeoLocation> loadCustomerNumbers(@Nonnull final Call call) {
        return call.getCustomerNumbers()
                .stream()
                .map(this::loadPhoneNumberGeoLocation)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<PhoneNumberGeoLocation> selectClosestGeographicMatch(@Nonnull final Call call,
                                                                          @Nonnull final PhoneNumberGeoLocation destinationNumber,
                                                                          @Nonnull final Collection<PhoneNumberGeoLocation> customerNumbers) {
        try {
            if (call.isCallToSameCountryOnly()) {
                return Optional.of(phoneMatcher.closestNumberMatchOnlySameCountry(destinationNumber, customerNumbers));
            }
            return Optional.of(phoneMatcher.closestNumberMatch(destinationNumber, customerNumbers));
        } catch (GeoMathException e) {
            errorMessagesHelper.addErrorMessage(e);
        }

        return Optional.empty();
    }

    private void errorMessageHelperLazyInitialization(@Nonnull final Call call) {
        errorMessagesHelper = new ErrorMessagesHelper<>(GetClosestGeographicMatchCommand.class, call.getUserLanguage());
    }
}
