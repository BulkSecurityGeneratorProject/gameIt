package mk.gameIt.web.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Stefan on 20.8.2016.
 */
public class LocationObject {
    private Long locationId;
    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "LocationObject{" +
                "locationId=" + locationId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", username='" + username + '\'' +
                '}';
    }
}
