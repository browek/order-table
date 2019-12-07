package com.table.order.foursquare.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.table.order.foursquare.model.FoundVenue;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class FoundVenueResponse {

//    @JsonProperty("venues")
    private Set<FoundVenue> venues;

    @SuppressWarnings("unchecked")
    @JsonProperty(value = "response")
    private void unpackResponse(Map<String,Object> response) {
        this.venues = new HashSet<>();

        List<Map> venues = (List<Map>) response.get("venues");

        for (Map venue : venues) {
            this.venues.add(FoundVenue.createFromJsonMap(venue));
        }
    }



}
