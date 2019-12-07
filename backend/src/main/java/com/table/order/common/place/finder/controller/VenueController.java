package com.table.order.common.place.finder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.table.order.common.place.finder.model.Request;
import com.table.order.common.place.finder.model.Venue;
import com.table.order.common.place.finder.service.VenueService;

@RestController
@RequestMapping(path = "/api/venues")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @PostMapping("/search")
    public List<Venue> searchForFoursquare(@RequestBody Request request) {
        return venueService.searchVenues(request);
    }


    @GetMapping("/searchBy")
    public List<Venue> searchVenuesByCity(@RequestParam(value="query", required = false) String query,
                                          @RequestParam(value ="city") String city){

        return venueService.searchVenuesByCity(query, city);

    }


}
