package com.table.order.foursquare.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoundVenue {

    private String foursquareId;
    private String name;
    private VenueLocation location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoundVenue foundVenue = (FoundVenue) o;
        return Objects.equals(foursquareId, foundVenue.foursquareId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foursquareId);
    }

    public static FoundVenue createFromJsonMap(Map<String, Object> venue) {
        Map<String, Object> location = (Map<String, Object>) venue.get("location");

        String address = (String) location.get("address");
        String city = (String) location.get("city");
        String country = (String) location.get("country");

        BigDecimal lat = new BigDecimal(String.valueOf(location.get("lat")));
        BigDecimal lng = new BigDecimal(String.valueOf(location.get("lng")));

        VenueLocation venueLocation = new VenueLocation(country, city, address, lat, lng);

        return new FoundVenue(
                (String) venue.get("id"),
                (String) venue.get("name"),
                venueLocation
        );
    }
}
