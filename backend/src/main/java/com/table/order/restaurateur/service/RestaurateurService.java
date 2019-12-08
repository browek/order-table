package com.table.order.restaurateur.service;

import java.util.List;
import java.util.Set;

import com.table.order.common.model.dto.ReservationDTO;
import com.table.order.common.security.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.table.order.client.service.ClientService;
import com.table.order.common.exceptions.VenueException;
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
import com.table.order.restaurateur.model.ActivatedRestaurant;
import com.table.order.restaurateur.model.Restaurant;
import com.table.order.restaurateur.repository.ActivatedRestaurantRepository;
import com.table.order.restaurateur.repository.RestaurantRepository;

@Service
public class RestaurateurService {

    private FoursquareService foursquareService;
    private UserHelper userHelper;
    private ActivatedRestaurantRepository activatedRestaurantRepository;
    private RestaurantRepository restaurantRepository;
    private ReservationRequestRepository reservationRequestRepository;
    private ClientService clientService;

    public RestaurateurService() {
    }

    @Autowired
    public RestaurateurService(FoursquareService foursquareService, UserHelper userHelper, 
    		RestaurantRepository restaurantRepository,  ClientService clientService,
			ActivatedRestaurantRepository activatedRestaurantRepository, ReservationRequestRepository reservationRequestRepository) {
		this.foursquareService = foursquareService;
		this.userHelper = userHelper;
		this.activatedRestaurantRepository = activatedRestaurantRepository;
		this.restaurantRepository = restaurantRepository;
		this.reservationRequestRepository = reservationRequestRepository;
		this.clientService = clientService;
	}

	public Set<FoundVenue> searchForRestaurant(String restaurant, String city) throws VenueException {
        if (StringUtils.isNullOrEmpty(restaurant) || StringUtils.isNullOrEmpty(city))
            throw new IncorrectRestaurantDataException();

        return foursquareService.searchForFoodVenues(restaurant, city);
    }

    public Restaurant saveRestaurantWithCurrentUser(String venueId) throws VenueException {
        if (StringUtils.isNullOrEmpty(venueId)) {
            throw new IncorrectRestaurantDataException();
        }
        
        Restaurant restaurant = findRestaurantOrCreateIfNotExists(venueId);
        restaurant.setActive(true);
        User loggedUser = userHelper.getLoggedUser();
        
        restaurant.setOwner(loggedUser);

		return restaurantRepository.save(restaurant);
    }
    
    private Restaurant findRestaurantOrCreateIfNotExists(String venueId) throws VenueException {
    	Restaurant restaurant = restaurantRepository.findByApiId(venueId);
    	
    	if (restaurant != null)
    		return restaurant;
        
    	return createRestaurant(venueId);
    }

    private Restaurant createRestaurant(String venueId) throws VenueException {
        VenueDetails venueDetails = foursquareService.getVenueDetails(venueId);
        
        Restaurant restaurant = new Restaurant();
        restaurant.setApiId(venueDetails.getFoursquareId());
        restaurant.setCity(venueDetails.getLocation().getCity());
        restaurant.setName(venueDetails.getName());
        restaurant.setStreet(venueDetails.getLocation().getCity());
        
        return restaurant;
    }

    public ActivatedRestaurant getRestaurantByApiId(String apiId) {
        return activatedRestaurantRepository.findByApiId(apiId);
    }

    public ReservationRequest acceptReservation(Long reservationId) throws UnauthorizedException {
        checkReservationOwner(reservationId);

        ReservationRequest reservationRequest = reservationRequestRepository.getOne(reservationId);
        reservationRequest.setStatus(ReservationRequestStatus.ACCEPTED);
        return reservationRequestRepository.save(reservationRequest);
    }

    public ReservationRequest rejectReservation(Long reservationId) throws UnauthorizedException {
        checkReservationOwner(reservationId);

        ReservationRequest reservationRequest = reservationRequestRepository.getOne(reservationId);
        reservationRequest.setStatus(ReservationRequestStatus.REJECTED);
        return reservationRequestRepository.save(reservationRequest);
    }

    private void checkReservationOwner(Long reservationId) throws UnauthorizedException {
        String loggedUserUsername = userHelper.getLoggedUserUsername();
        if (!reservationRequestRepository.existsByRestaurantOwnerUsernameAndId(loggedUserUsername, reservationId))
            throw new UnauthorizedException();

    }

    public boolean restaurantIsRegistered(String venueApiId) {
        return activatedRestaurantRepository.existsByApiId(venueApiId);
    }

    public ActivatedRestaurant deleteRestaurant(ActivatedRestaurant restaurant) {
        restaurant.setActive(false);
        restaurant.getReservationRequests().forEach(r -> clientService.deleteReservation(r));
        return activatedRestaurantRepository.save(restaurant);
    }

    public List<ReservationDTO> getReservationsByRestaurantId(
            Integer restaurantId,
            ReservationRequestStatus status) {

        String loggedUserUsername = userHelper.getLoggedUserUsername();
        return reservationRequestRepository.findAllByRestaurantIdAndStatus(
                restaurantId,
                status,
                loggedUserUsername
        );
    }


    @Autowired
    public void setFoursquareService(FoursquareService foursquareService) {
        this.foursquareService = foursquareService;
    }
}
