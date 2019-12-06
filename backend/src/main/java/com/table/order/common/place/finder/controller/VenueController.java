package com.table.order.common.place.finder.controller;

import com.table.order.common.place.finder.model.Request;
import com.table.order.common.place.finder.model.Venue;
import com.table.order.common.place.finder.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RepositoryRestController
@RequestMapping(path = "/api/venues")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @PostMapping("/search")
    public ResponseEntity<?> searchForFoursquare(@RequestBody Request request) {
        List<Venue> venues = venueService.searchVenues(request);

        return ResponseEntity.ok(venues);
    }


}
