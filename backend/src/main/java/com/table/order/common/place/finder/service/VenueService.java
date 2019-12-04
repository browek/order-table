package com.table.order.common.place.finder.service;

import com.table.order.common.place.finder.model.Location;
import com.table.order.common.place.finder.model.Request;
import com.table.order.common.place.finder.model.Venue;
import com.table.order.common.place.finder.model.froursquare.dto.Group;
import com.table.order.common.place.finder.model.froursquare.dto.Item;
import com.table.order.common.place.finder.model.froursquare.dto.ResponseFromFoursquare;
import com.table.order.common.place.finder.model.froursquare.dto.VenueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VenueService {

    @Autowired
    private FoursquareApiComponent foursquareApiComponent;


    public List<Venue> searchVenues(Request request) {
        String url = foursquareApiComponent.getFoursquareExploreUrl();
        Map<String, Object> uriVariables = foursquareApiComponent.getFoursquareUriVariables(request);

        RestTemplate restTemplate = new RestTemplate();
        ResponseFromFoursquare foursquareResponse = restTemplate.getForObject(url, ResponseFromFoursquare.class, uriVariables);

        return getVenuesFromResponse(foursquareResponse);
    }


    public List<Venue> getVenuesFromResponse(ResponseFromFoursquare responseFromFoursquare) {
        Group group = responseFromFoursquare.getResponse().getGroups().get(0);

        return group.getItems().stream().map(this::getVenueFromItem).collect(Collectors.toList());
    }

    public Venue getVenueFromItem(Item item) {
        VenueDTO venueDTO = item.getVenue();
        Location location = Location.createByDto(venueDTO.getLocation());

        Venue venue = Venue.createByDto(venueDTO);
        venue.setLocation(location);

        return venue;
    }


}


