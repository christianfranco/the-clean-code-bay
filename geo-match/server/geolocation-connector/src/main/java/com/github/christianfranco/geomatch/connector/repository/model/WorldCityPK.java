package com.github.christianfranco.geomatch.connector.repository.model;

import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

import static java.util.Objects.hash;

/**
 * Created by Christian Franco on 15/12/2016.
 */
@Embeddable
public class WorldCityPK implements Serializable {
    private static final long serialVersionUID = 4345145246210233014L;

    private String country;
    private String city;
    private String region;

    public WorldCityPK() {
    }

    @Column(name = "COUNTRY", length = 5, nullable = false)
    public String getCountry() {
        return country;
    }

    @Column(name = "CITY", length = 150, nullable = false)
    public String getCity() {
        return city;
    }

    @Column(name = "REGION", length = 5, nullable = false)
    public String getRegion() {
        return region;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public int hashCode() {
        return hash(country, city, region);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }
        WorldCityPK other = (WorldCityPK) obj;
        return new EqualsBuilder()
                .append(country, other.country)
                .append(city, other.city)
                .append(region, other.region)
                .isEquals();
    }
}
