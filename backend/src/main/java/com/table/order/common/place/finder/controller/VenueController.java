package com.table.order.common.place.finder.controller;

import java.util.List;

import com.table.order.common.model.dto.VenueMapDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<VenueMapDTO>> searchForFoursquare(@RequestBody Request request) {
        return ResponseEntity.ok(venueService.searchVenues(request));
    }


    @GetMapping("/searchBy")
    public ResponseEntity<List<VenueMapDTO>> searchVenuesByCity(@RequestParam(value="query", required = false) String query,
                                                                @RequestParam(value ="city") String city){

        return ResponseEntity.ok(venueService.searchVenuesByCity(query, city));

    }


}
