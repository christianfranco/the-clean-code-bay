package com.github.christianfranco.geomatch.connector.mapper;

import com.github.christianfranco.geomatch.entities.Call;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 * Created by Christian Franco on 18/12/2016.
 */
@XmlRootElement
public class CallValueObject implements Call {

    private String destinationNumber;
    private Collection<String> customerNumbers;
    private String userLanguage;
    private boolean callToSameCountryOnly;

    public CallValueObject() {
    }

    public String getDestinationNumber() {
        return destinationNumber;
    }

    public CallValueObject setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
        return this;
    }

    public Collection<String> getCustomerNumbers() {
        return customerNumbers;
    }

    public CallValueObject setCustomerNumbers(Collection<String> customerNumbers) {
        this.customerNumbers = customerNumbers;
        return this;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public CallValueObject setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
        return this;
    }

    public boolean isCallToSameCountryOnly() {
        return callToSameCountryOnly;
    }

    public CallValueObject setCallToSameCountryOnly(boolean callToSameCountryOnly) {
        this.callToSameCountryOnly = callToSameCountryOnly;
        return this;
    }
}
