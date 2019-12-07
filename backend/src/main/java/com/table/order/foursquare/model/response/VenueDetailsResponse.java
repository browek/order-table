package com.table.order.foursquare.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.table.order.foursquare.model.FoundVenue;
import com.table.order.foursquare.model.VenueDetails;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
public class VenueDetailsResponse {

    private VenueDetails venueDetails;

    @SuppressWarnings("unchecked")
    @JsonProperty(value = "response")
    private void unpackResponse(Map<String,Object> response) {
        Map venue = (Map) response.get("venue");
        String rating = venue.get("rating") != null ? String.valueOf(venue.get("rating")): "0";
        this.venueDetails = VenueDetails.createVenuesDetails(
                FoundVenue.createFromJsonMap(venue),
                (String) venue.get("url"),
                new BigDecimal(rating));

    }

}
