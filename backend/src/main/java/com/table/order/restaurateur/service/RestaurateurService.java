package com.table.order.restaurateur.service;

import com.table.order.common.repository.ReservationRequestRepository;
import com.table.order.common.security.exception.UnauthorizedException;
import com.table.order.common.service.UserService;
import com.table.order.common.util.StringUtils;
import com.table.order.foursquare.FoursquareService;
import com.table.order.foursquare.model.FoundVenue;
import com.table.order.foursquare.model.VenueDetails;
import com.table.order.restaurateur.exception.IncorrectRestaurantDataException;
import com.table.order.restaurateur.exception.NoRestaurantAssigned;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RestaurateurService {

    private FoursquareService foursquareService;
    private UserService userService;

    @Autowired
    private ReservationRequestRepository reservationRequestRepository;

    public RestaurateurService(FoursquareService foursquareService, UserService userService) {
        this.foursquareService = foursquareService;
        this.userService = userService;
    }

    public Set<FoundVenue> searchForRestaurant(String restaurant, String city) {
        if (StringUtils.isNullOrEmpty(restaurant) || StringUtils.isNullOrEmpty(city))
            throw new IncorrectRestaurantDataException();

        return foursquareService.searchForFoodVenues(restaurant, city);
    }

    public VenueDetails assignRestaurantAndGetItsDetails(String venueId) throws UnauthorizedException {
        if (StringUtils.isNullOrEmpty(venueId))
            throw new IncorrectRestaurantDataException();

        userService.assignRestaurantToLoggedUser(venueId);

        return foursquareService.getVenueDetails(venueId);
    }

    public VenueDetails getOwnRestaurantDetails() throws NoRestaurantAssigned {
        String restaurantIdOfLoggedUser
                = userService.getRestaurantIdOfLoggedUser();

        if (restaurantIdOfLoggedUser == null)
            throw new NoRestaurantAssigned();

        return foursquareService.getVenueDetails(restaurantIdOfLoggedUser);
    }


    public void unassignRestaurant() throws UnauthorizedException {
        userService.removeRestaurantFromLoggedUser();
    }


//    public ReservationRequest acceptReservation(Long reservationId) {
//
//        ReservationRequest reservationRequest = reservationRequestRepository.findById(reservationId);
//        reservationRequest.setStatus(ReservationRequestStatus.ACCEPTED);
//        return reservationRequestRepository.save(reservationRequest);
//    }
//
//    public ReservationRequest rejectReservation(int reservationId){
//        ReservationRequest reservationRequest = reservationRequestRepository.findById(reservationId);
//        reservationRequest.setStatus(ReservationRequestStatus.REJECTED);
//        return reservationRequestRepository.save(reservationRequest);
//    }
}
