package com.github.christianfranco.geomatch.command.phonenumber;

import com.github.christianfranco.geomatch.entities.*;
import com.github.christianfranco.geomatch.exception.InvalidPhoneNumberFormatException;
import com.github.christianfranco.geomatch.helper.PhoneMatcherImpl;
import com.github.christianfranco.geomatch.repository.GeoLocationRepository;
import com.github.christianfranco.geomatch.repository.PhoneNumberRepository;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by Christian Franco on 16/12/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class GetClosestGeographicMatchCommandTest {

    @Mock
    private GeoLocationRepository geoLocationRepository;

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    private GetClosestGeographicMatchCommand geographicMatchCommand;

    private PhoneNumberGeoLocation fr;
    private PhoneNumberGeoLocation gb;
    private PhoneNumberGeoLocation us;

    private PhoneNumberGeoLocation porto;
    private PhoneNumberGeoLocation lisbon;
    private PhoneNumberGeoLocation setubal;

    @Before
    public void setUp() throws Exception {
        geographicMatchCommand = new GetClosestGeographicMatchCommand(geoLocationRepository, phoneNumberRepository, new PhoneMatcherImpl());

        fr = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(2.0,2.0),
                ImmutablePhoneNumber.of(33, 975180000L, ImmutableLocale.of("FR", "city"))
        );
        when(geoLocationRepository.getGeoSpacialPoint(fr.getPhoneNumber().getLocale())).thenReturn(fr.getPoint());
        when(phoneNumberRepository.getPhoneNumber(fr.getPhoneNumber().toE164Format())).thenReturn(fr.getPhoneNumber());

        gb = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(10.0,10.0),
                ImmutablePhoneNumber.of(44, 1732600000L, ImmutableLocale.of("GB", "city"))
        );
        when(geoLocationRepository.getGeoSpacialPoint(gb.getPhoneNumber().getLocale())).thenReturn(gb.getPoint());
        when(phoneNumberRepository.getPhoneNumber(gb.getPhoneNumber().toE164Format())).thenReturn(gb.getPhoneNumber());

        us = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(100.0,100.0),
                ImmutablePhoneNumber.of(1, 4159690000L, ImmutableLocale.of("US", "city"))
        );
        when(geoLocationRepository.getGeoSpacialPoint(us.getPhoneNumber().getLocale())).thenReturn(us.getPoint());
        when(phoneNumberRepository.getPhoneNumber(us.getPhoneNumber().toE164Format())).thenReturn(us.getPhoneNumber());

        porto = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(6.9, 6.9),
                ImmutablePhoneNumber.of(351, 222220000, ImmutableLocale.of("PT", "porto"))
        );
        when(geoLocationRepository.getGeoSpacialPoint(porto.getPhoneNumber().getLocale())).thenReturn(porto.getPoint());
        when(phoneNumberRepository.getPhoneNumber(porto.getPhoneNumber().toE164Format())).thenReturn(porto.getPhoneNumber());

        lisbon = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(6.5, 6.5),
                ImmutablePhoneNumber.of(351, 211230000L, ImmutableLocale.of("PT", "lisbon"))
        );
        when(geoLocationRepository.getGeoSpacialPoint(lisbon.getPhoneNumber().getLocale())).thenReturn(lisbon.getPoint());
        when(phoneNumberRepository.getPhoneNumber(lisbon.getPhoneNumber().toE164Format())).thenReturn(lisbon.getPhoneNumber());

        setubal = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(6.4, 6.4),
                ImmutablePhoneNumber.of(351, 265120000L, ImmutableLocale.of("PT", "setubal"))
        );
        when(geoLocationRepository.getGeoSpacialPoint(setubal.getPhoneNumber().getLocale())).thenReturn(setubal.getPoint());
        when(phoneNumberRepository.getPhoneNumber(setubal.getPhoneNumber().toE164Format())).thenReturn(setubal.getPhoneNumber());
    }

    @Test
    public void geoMatchForCloserCountry() throws Exception {
        Call callToFrance = ImmutableCall.builder()
                .destinationNumber(fr.getPhoneNumber().toE164Format())
                .customerNumbers(ImmutableList.of(gb.getPhoneNumber().toE164Format(), us.getPhoneNumber().toE164Format())).build();

        CallResponse responseGB = geographicMatchCommand.call(callToFrance);

        assertThat(responseGB.getSelectedCustomerNumber(), is(gb.getPhoneNumber().toE164Format()));
        assertThat(responseGB.getLocation(), is(gb.getPhoneNumber().getLocale().asString()));
        assertThat(responseGB.getErrorMessages().isEmpty(), is(true));
    }

    @Test
    public void geoMatchForCloserCity() throws Exception {
        Call callToSetubal = ImmutableCall.builder()
                .destinationNumber(setubal.getPhoneNumber().toE164Format())
                .customerNumbers(ImmutableList.of(porto.getPhoneNumber().toE164Format(), lisbon.getPhoneNumber().toE164Format())).build();

        CallResponse responseLisbon = geographicMatchCommand.call(callToSetubal);

        assertThat(responseLisbon.getSelectedCustomerNumber(), is(lisbon.getPhoneNumber().toE164Format()));
        assertThat(responseLisbon.getLocation(), is(lisbon.getPhoneNumber().getLocale().asString()));
        assertThat(responseLisbon.getErrorMessages().isEmpty(), is(true));
    }

    @Test
    public void geoMatchForTollFreeNumber() throws Exception {
        PhoneNumberGeoLocation gbTollFree = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(10.0,10.0),
                ImmutablePhoneNumber.of(44, 8008080000L, ImmutableLocale.of("GB", "toll-free"))
        );
        when(geoLocationRepository.getGeoSpacialPoint(gbTollFree.getPhoneNumber().getLocale())).thenReturn(gbTollFree.getPoint());
        when(phoneNumberRepository.getPhoneNumber(gbTollFree.getPhoneNumber().toE164Format())).thenReturn(gbTollFree.getPhoneNumber());

        PhoneNumberGeoLocation usTollFree = ImmutablePhoneNumberGeoLocation.of(
                ImmutablePoint.of(15.0,10.0),
                ImmutablePhoneNumber.of(1, 8009970000L, ImmutableLocale.of("US", "toll-free"))
        );
        when(geoLocationRepository.getGeoSpacialPoint(usTollFree.getPhoneNumber().getLocale())).thenReturn(usTollFree.getPoint());
        when(phoneNumberRepository.getPhoneNumber(usTollFree.getPhoneNumber().toE164Format())).thenReturn(usTollFree.getPhoneNumber());

        Call callToPortugal= ImmutableCall.builder()
                .destinationNumber(lisbon.getPhoneNumber().toE164Format())
                .customerNumbers(ImmutableList.of(usTollFree.getPhoneNumber().toE164Format(), gbTollFree.getPhoneNumber().toE164Format())).build();

        CallResponse responseGB = geographicMatchCommand.call(callToPortugal);

        assertThat(responseGB.getSelectedCustomerNumber(), is(gbTollFree.getPhoneNumber().toE164Format()));
        assertThat(responseGB.getLocation(), is(gbTollFree.getPhoneNumber().getLocale().asString()));
        assertThat(responseGB.getErrorMessages().isEmpty(), is(true));
    }

    @Test
    public void geoMatchForCloserCity_sameCountryOnly() throws Exception {
        Call callToSetubal = ImmutableCall.builder()
                .destinationNumber(setubal.getPhoneNumber().toE164Format())
                .customerNumbers(ImmutableList.of(porto.getPhoneNumber().toE164Format(), lisbon.getPhoneNumber().toE164Format()))
                .isCallToSameCountryOnly(true).build();

        CallResponse responseLisbon = geographicMatchCommand.call(callToSetubal);

        assertThat(responseLisbon.getSelectedCustomerNumber(), is(lisbon.getPhoneNumber().toE164Format()));
        assertThat(responseLisbon.getLocation(), is(lisbon.getPhoneNumber().getLocale().asString()));
        assertThat(responseLisbon.getErrorMessages().isEmpty(), is(true));
    }

    @Test
    public void geoMatchForCloserCity_sameCountryOnly_matchNotFound() throws Exception {
        Call callToSameCountryOnly = ImmutableCall.builder()
                .destinationNumber(fr.getPhoneNumber().toE164Format())
                .customerNumbers(ImmutableList.of(gb.getPhoneNumber().toE164Format(), us.getPhoneNumber().toE164Format()))
                .isCallToSameCountryOnly(true).build();

        CallResponse responseNoMatchFound = geographicMatchCommand.call(callToSameCountryOnly);

        assertThat(responseNoMatchFound.getSelectedCustomerNumber(), is(""));
        assertThat(responseNoMatchFound.getLocation(), is(""));
        assertThat(responseNoMatchFound.getErrorMessages(), containsInAnyOrder("No match found."));
    }

    @Test
    public void geoMatch_atLeastOneInvalidCustomerNumber() throws Exception {
        Call callToSetubal= ImmutableCall.builder()
                .destinationNumber(setubal.getPhoneNumber().toE164Format())
                .customerNumbers(ImmutableList.of(lisbon.getPhoneNumber().toE164Format(), "1230000")).build();

        doThrow(new InvalidPhoneNumberFormatException(ErrorCode.PHONE_ERROR_00002, "1230000"))
                .when(phoneNumberRepository).getPhoneNumber("1230000");

        CallResponse responseLisbon = geographicMatchCommand.call(callToSetubal);

        assertThat(responseLisbon.getSelectedCustomerNumber(), is(lisbon.getPhoneNumber().toE164Format()));
        assertThat(responseLisbon.getLocation(), is(lisbon.getPhoneNumber().getLocale().asString()));
        assertThat(responseLisbon.getErrorMessages(), containsInAnyOrder("The phone number (1230000) has a wrong format."));
    }

    @Test
    public void geoMatch_all_customerNumber_invalid() throws Exception {
        Call callToSetubal= ImmutableCall.builder()
                .destinationNumber(setubal.getPhoneNumber().toE164Format())
                .customerNumbers(ImmutableList.of("+1235", "1230000")).build();

        doThrow(new InvalidPhoneNumberFormatException(ErrorCode.PHONE_ERROR_00002, "+1235"))
                .when(phoneNumberRepository).getPhoneNumber("+1235");

        doThrow(new InvalidPhoneNumberFormatException(ErrorCode.PHONE_ERROR_00002, "1230000"))
                .when(phoneNumberRepository).getPhoneNumber("1230000");

        CallResponse responseLisbon = geographicMatchCommand.call(callToSetubal);

        assertThat(responseLisbon.getSelectedCustomerNumber(), is(""));
        assertThat(responseLisbon.getLocation(), is(""));
        assertThat(responseLisbon.getErrorMessages(), containsInAnyOrder(
                "The phone number (+1235) has a wrong format.",
                "The phone number (1230000) has a wrong format.",
                "It was not possible to selected a number. All customer numbers are invalid."));
    }

    @Test
    public void geoMatch_destinationNumber_invalid() throws Exception {
        Call callInvalidDestinationNumber= ImmutableCall.builder()
                .destinationNumber("120000")
                .customerNumbers(ImmutableList.of("+351222220000", "+351211230000")).build();

        doThrow(new InvalidPhoneNumberFormatException(ErrorCode.PHONE_ERROR_00002, "120000"))
                .when(phoneNumberRepository).getPhoneNumber("120000");

        CallResponse responseLisbon = geographicMatchCommand.call(callInvalidDestinationNumber);

        assertThat(responseLisbon.getSelectedCustomerNumber(), is(""));
        assertThat(responseLisbon.getLocation(), is(""));
        assertThat(responseLisbon.getErrorMessages(), containsInAnyOrder(
                "The phone number (120000) has a wrong format.",
                "It was not possible to selected a number. The destination number is invalid."));
    }
}
