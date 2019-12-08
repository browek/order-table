package com.table.order.restaurateur.controller;

import com.table.order.foursquare.model.FoundVenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.table.order.restaurateur.model.Restaurant;
import com.table.order.restaurateur.service.RestaurateurService;

import java.util.Set;

@RepositoryRestController
@RequestMapping("/api/restaurants")
public class RestaurateurController {

    private RestaurateurService restaurateurService;

    @Autowired
    public RestaurateurController(RestaurateurService restaurateurService) {
        this.restaurateurService = restaurateurService;
    }
    
    @PreAuthorize("hasRole('ROLE_RESTAURATEUR')")
    @PutMapping("/assign")
    public ResponseEntity<Restaurant> assignRestaurantToRestauretur(
    		@RequestParam("foursquare_id") String foursquareRestaurantId) {

        Restaurant restaurant = restaurateurService.saveRestaurantWithCurrentUser(foursquareRestaurantId);
    	return new ResponseEntity<>(restaurant, HttpStatus.OK);
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
