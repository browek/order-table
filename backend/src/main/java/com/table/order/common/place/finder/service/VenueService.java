package com.table.order.common.place.finder.service;

import com.table.order.common.model.dto.VenueMapDTO;
import com.table.order.common.place.finder.model.Location;
import com.table.order.common.place.finder.model.Request;
import com.table.order.common.place.finder.model.Venue;
import com.table.order.common.place.finder.model.froursquare.dto.Group;
import com.table.order.common.place.finder.model.froursquare.dto.Item;
import com.table.order.common.place.finder.model.froursquare.dto.ResponseFromFoursquare;
import com.table.order.common.place.finder.model.froursquare.dto.VenueDTO;
import com.table.order.common.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VenueService {

    private FoursquareApiComponent foursquareApiComponent;
    private UserService userService;

    public List<VenueMapDTO> searchVenuesByCity(String query, String city){
        String url = foursquareApiComponent.getFoursquareExploreUrlByCity();
        Map<String, Object> uriVariables = foursquareApiComponent.getFoursquareUriVariablesByCity(query, city);

        RestTemplate restTemplate = new RestTemplate();
        ResponseFromFoursquare foursquareResponse = restTemplate.getForObject(url, ResponseFromFoursquare.class, uriVariables);

        return convertToMapDTO(getVenuesFromResponse(foursquareResponse));
    }

    private List<VenueMapDTO> convertToMapDTO(List<Venue> venues) {
        return venues.stream().map(venue -> {
            VenueMapDTO dto = new ModelMapper().map(venue, VenueMapDTO.class);
            dto.setId(venue.getApiId());
            dto.setIsRegistered(userService.isRestaurateurRegistered(venue.getApiId()));
            return dto;
        }).collect(Collectors.toList());
    }

    public List<VenueMapDTO> searchVenues(Request request) {
        String url = foursquareApiComponent.getFoursquareExploreUrl();
        Map<String, Object> uriVariables = foursquareApiComponent.getFoursquareUriVariables(request);

        RestTemplate restTemplate = new RestTemplate();
        ResponseFromFoursquare foursquareResponse = restTemplate.getForObject(url, ResponseFromFoursquare.class, uriVariables);

        return convertToMapDTO(getVenuesFromResponse(foursquareResponse));
    }


    public List<Venue> getVenuesFromResponse(ResponseFromFoursquare responseFromFoursquare) {
        Group group = responseFromFoursquare.getResponse().getGroups().get(0);

        return group.getItems().stream().map(this::getVenueFromItem).collect(Collectors.toList());
    }

    public Venue getVenueFromItem(Item item) {
        VenueDTO venueMapDTO = item.getVenue();
        Location location = Location.createByDto(venueMapDTO.getLocation());

        Venue venue = Venue.createByDto(venueMapDTO);
        venue.setLocation(location);

        return venue;
    }

    @Autowired
    public void setFoursquareApiComponent(FoursquareApiComponent foursquareApiComponent) {
        this.foursquareApiComponent = foursquareApiComponent;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}


