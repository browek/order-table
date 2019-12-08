package com.table.order.restaurateur.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.table.order.common.model.ReservationRequest;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.common.repository.ReservationRequestRepository;
import com.table.order.common.security.model.User;
import com.table.order.common.service.helper.UserHelper;
import com.table.order.common.util.StringUtils;
import com.table.order.foursquare.FoursquareService;
import com.table.order.foursquare.model.FoundVenue;
import com.table.order.foursquare.model.VenueDetails;
import com.table.order.restaurateur.exception.IncorrectRestaurantDataException;
import com.table.order.restaurateur.model.Restaurant;
import com.table.order.restaurateur.repository.RestaurantRepository;

@Service
public class RestaurateurService {

    private FoursquareService foursquareService;
    private UserHelper userHelper;
    private RestaurantRepository restaurantRepository; 
    private ReservationRequestRepository reservationRequestRepository;

    @Autowired
    public RestaurateurService(FoursquareService foursquareService, UserHelper userHelper,
			RestaurantRepository restaurantRepository, ReservationRequestRepository reservationRequestRepository) {
		this.foursquareService = foursquareService;
		this.userHelper = userHelper;
		this.restaurantRepository = restaurantRepository;
		this.reservationRequestRepository = reservationRequestRepository;
	}

	public Set<FoundVenue> searchForRestaurant(String restaurant, String city) {
        if (StringUtils.isNullOrEmpty(restaurant) || StringUtils.isNullOrEmpty(city))
            throw new IncorrectRestaurantDataException();

        return foursquareService.searchForFoodVenues(restaurant, city);
    }

    public Restaurant saveRestaurantWithCurrentUser(String venueId) {
        if (StringUtils.isNullOrEmpty(venueId)) {
            throw new IncorrectRestaurantDataException();
        }
        
        Restaurant restaurant = findRestaurantOrGetNewIfNotExists(venueId);
        User loggedUser = userHelper.getLoggedUser();
        
        restaurant.setOwner(loggedUser);

		return restaurantRepository.save(restaurant);
    }
    
    private Restaurant findRestaurantOrGetNewIfNotExists(String venueId) {
    	Restaurant restaurant = restaurantRepository.findByApiId(venueId);
    	
    	if (restaurant != null)
    		return restaurant;
        
    	return createRestaurant(venueId);
    }

    private Restaurant createRestaurant(String venueId) {
        VenueDetails venueDetails = foursquareService.getVenueDetails(venueId);
        
        Restaurant restaurant = new Restaurant();
        restaurant.setApiId(venueDetails.getFoursquareId());
        restaurant.setCity(venueDetails.getLocation().getCity());
        restaurant.setName(venueDetails.getName());
        restaurant.setStreet(venueDetails.getLocation().getCity());
        
        return restaurant;
    }

    public Restaurant getRestaurantByApiId(String apiId) {
        return restaurantRepository.findByApiId(apiId);
    }

    public ReservationRequest acceptReservation(Long reservationId) {
        ReservationRequest reservationRequest = reservationRequestRepository.findByLongId(reservationId);
        reservationRequest.setStatus(ReservationRequestStatus.ACCEPTED);
        return reservationRequestRepository.save(reservationRequest);
    }

    public ReservationRequest rejectReservation(Long reservationId) {
        ReservationRequest reservationRequest = reservationRequestRepository.findByLongId(reservationId);
        reservationRequest.setStatus(ReservationRequestStatus.REJECTED);
        return reservationRequestRepository.save(reservationRequest);
    }

    public boolean restaurantIsRegistered(String venueApiId) {
        return restaurantRepository.existsByApiId(venueApiId);
    }
}
