package com.github.christianfranco.geomatch.client.json;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Christian Franco on 19/12/2016.
 */
@XmlRootElement
public class ResponseJson implements Serializable{
    private static final long serialVersionUID = 33345246210233014L;

    private String selectedCustomerNumber;
    private String location;
    private Collection<String> errorMessages;

    public String getSelectedCustomerNumber() {
        return selectedCustomerNumber == null ? "" : selectedCustomerNumber;
    }

    public ResponseJson setSelectedCustomerNumber(String selectedCustomerNumber) {
        this.selectedCustomerNumber = selectedCustomerNumber;
        return this;
    }

    public String getLocation() {
        return location == null ? "" : location;
    }

    public ResponseJson setLocation(String location) {
        this.location = location;
        return this;
    }

    public Collection<String> getErrorMessages() {
        return errorMessages == null ? new HashSet<>() : errorMessages;
    }

    public ResponseJson setErrorMessages(Collection<String> errorMessages) {
        this.errorMessages = errorMessages;
        return this;
    }
}
