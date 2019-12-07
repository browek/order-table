package com.table.order.restaurateur.controller;

import com.table.order.common.security.exception.UnauthorizedException;
import com.table.order.foursquare.model.FoundVenue;
import com.table.order.foursquare.model.VenueDetails;
import com.table.order.restaurateur.exception.NoRestaurantAssigned;
import com.table.order.restaurateur.service.RestaurateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurateurController {

    private RestaurateurService restaurateurService;

    public RestaurateurController(RestaurateurService restaurateurService) {
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

    @PreAuthorize("hasAuthority('ROLE_RESTAURATEUR')")
    @PutMapping("/assign")
    public ResponseEntity<VenueDetails> assignRestaurantToRestauretur(
            @RequestParam("foursquare_id") String foursquareRestaurantId) {

        try {
            VenueDetails details
                    = restaurateurService.assignRestaurantAndGetItsDetails(foursquareRestaurantId);

            return ResponseEntity.ok(details);
        } catch (UnauthorizedException e) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_RESTAURATEUR')")
    @PutMapping("/unassign")
    public ResponseEntity<?> unassignRestaurantFromRestaurateur() {
        try {
            restaurateurService.unassignRestaurant();
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(null);
    }

    @PreAuthorize("hasAuthority('ROLE_RESTAURATEUR')")
    @GetMapping("/own")
    public ResponseEntity<VenueDetails> getRestaurateurRestaurantDetails() {

        try {
            VenueDetails ownRestaurantDetails =
                    restaurateurService.getOwnRestaurantDetails();

            return ResponseEntity.ok(ownRestaurantDetails);
        } catch (NoRestaurantAssigned noRestaurantAssigned) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/reservation/accept")
//    public ResponseEntity<?> acceptReservation(@RequestParam("reservation_id") Long reservationId){
//        restaurateurService.acceptReservation(reservationId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/reservation/reject")
//    public ResponseEntity<?> rejectReservation(@RequestParam("reservation_id") int reservationId){
//        restaurateurService.rejectReservation(reservationId);
//        return ResponseEntity.ok().build();
//    }



}
