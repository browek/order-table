package com.table.order.foursquare.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class VenueDetails extends FoundVenue {

    private String websiteUrl;
    private BigDecimal rating;

    private VenueDetails(FoundVenue foundVenue) {
        super(foundVenue.getFoursquareId(), foundVenue.getName(), foundVenue.getLocation());
    }

    public static VenueDetails createVenuesDetails(FoundVenue foundVenue,
                                                   String websiteUrl,
                                                   BigDecimal rating) {
        VenueDetails venueDetails = new VenueDetails(foundVenue);
        venueDetails.setWebsiteUrl(websiteUrl);
        venueDetails.setRating(rating);
        return venueDetails;
    }
}
