package com.table.order.restaurateur.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.table.order.foursquare.model.FoundVenue;
import com.table.order.restaurateur.service.RestaurateurService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurateurRestController {

    private RestaurateurService restaurateurService;

    public RestaurateurRestController(RestaurateurService restaurateurService) {
        this.restaurateurService = restaurateurService;
    }

    @PreAuthorize("hasAuthority('ROLE_RESTAURATEUR')")
    @GetMapping("/search")
    public ResponseEntity<Set<FoundVenue>> searchRestaurants(
            @RequestParam("restaurant_name") String restaurantName,
            @RequestParam("city") String city) {

        Set<FoundVenue> foundVenues
                = restaurateurService.searchForRestaurant(restaurantName, city);

        return ResponseEntity.ok(foundVenues);
    }

    @PutMapping("/reservation/accept")
    public ResponseEntity<?> acceptReservation(@RequestParam("reservation_id") Long reservationId) {
        restaurateurService.acceptReservation(reservationId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reservation/reject")
    public ResponseEntity<?> rejectReservation(@RequestParam("reservation_id") Long reservationId) {
        restaurateurService.rejectReservation(reservationId);
        return ResponseEntity.ok().build();
    }


}
