package com.github.christianfranco.geomatch.client.json;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Christian Franco on 19/12/2016.
 */
@XmlRootElement
public class RequestJson implements Serializable {
    private static final long serialVersionUID = 775145246210233014L;

    private String destinationNumber;
    private Collection<String> customerNumbers;
    private String userLanguage;
    private boolean callToSameCountryOnly;

    public String getDestinationNumber() {
        return destinationNumber == null ? "" : destinationNumber;
    }

    public RequestJson setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
        return this;
    }

    public Collection<String> getCustomerNumbers() {
        return customerNumbers == null ? new HashSet<>() : customerNumbers;
    }

    public RequestJson setCustomerNumbers(Collection<String> customerNumbers) {
        this.customerNumbers = customerNumbers;
        return this;
    }

    public String getUserLanguage() {
        return userLanguage == null ? "en" : userLanguage;
    }

    public RequestJson setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
        return this;
    }

    public boolean isCallToSameCountryOnly() {
        return callToSameCountryOnly;
    }

    public RequestJson setCallToSameCountryOnly(boolean callToSameCountryOnly) {
        this.callToSameCountryOnly = callToSameCountryOnly;
        return this;
    }
}
