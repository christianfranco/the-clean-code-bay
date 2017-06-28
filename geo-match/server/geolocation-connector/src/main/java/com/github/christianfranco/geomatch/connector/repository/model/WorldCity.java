package com.github.christianfranco.geomatch.connector.repository.model;

import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;
import java.io.Serializable;

import static java.util.Objects.hash;

/**
 * Created by Christian Franco on 15/12/2016.
 */
@Entity
@Table(name = "WORLD_CITY")
@NamedQueries({
        @NamedQuery(name = WorldCity.WORLD_CITY_QUERY_BY_COUNTRY_CITY,
                query = "SELECT w FROM WorldCity w WHERE w.worldCityPK.country = :countryName and w.worldCityPK.city = :cityName"),
        @NamedQuery(name = WorldCity.WORLD_CITY_QUERY_BY_COUNTRY,
                query = "SELECT w FROM WorldCity w WHERE w.worldCityPK.country = :countryName and ROWNUM <=1")})
public class WorldCity implements Serializable {
    private static final long serialVersionUID = 8473145246210233014L;

    public static final String WORLD_CITY_QUERY_BY_COUNTRY_CITY = "WorldCity.queryByCountryCity";
    public static final String WORLD_CITY_QUERY_BY_COUNTRY = "WorldCity.queryByCountry";

    private WorldCityPK worldCityPK;
    private Double latitude;
    private Double longitude;

    public WorldCity() {
    }

    @EmbeddedId
    public WorldCityPK getWorldCityPK() {
        return worldCityPK;
    }

    public void setWorldCityPK(WorldCityPK worldCityPK) {
        this.worldCityPK = worldCityPK;
    }

    @Column(name = "LATITUDE", nullable = false)
    public Double getLatitude() {
        return latitude;
    }

    @Column(name = "LONGITUDE", nullable = false)
    public Double getLongitude() {
        return longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        return hash(worldCityPK);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }
        WorldCity other = (WorldCity) obj;
        return new EqualsBuilder().append(worldCityPK, other.worldCityPK).isEquals();
    }
}
