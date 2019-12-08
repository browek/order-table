package com.table.order.common.client.service;


import com.table.order.common.client.model.ClientReservation;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.common.repository.ReservationRequestRepository;
import com.table.order.common.repository.UserRepository;
import com.table.order.common.security.model.User;
import com.table.order.common.service.helper.UserHelper;
import com.table.order.restaurateur.exception.IncorrectRestaurantDataException;
import com.table.order.restaurateur.model.Restaurant;
import com.table.order.restaurateur.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRequestRepository reservationRequestRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserHelper userHelper;


    public ReservationRequest sendReservationRequest(ClientReservation clientReservation) {
        Restaurant restaurant = restaurantRepository.findByApiId(clientReservation.getId());
        User restaurantOwner = restaurant.getOwner();
        if (restaurantOwner == null) {
            throw new IncorrectRestaurantDataException();
        }
        return createNewReservation(clientReservation, restaurantOwner);
    }

    private ReservationRequest createNewReservation(ClientReservation clientReservation, User restaurantOwner) {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setAskingUser(userHelper.getLoggedUser());
        reservationRequest.setRecivingUser(restaurantOwner);
        reservationRequest.setDateAndTime(clientReservation.getDateAndTime());
        reservationRequest.setNumberOfPersons(clientReservation.getNumberOfPersons());
        reservationRequest.setStatus(ReservationRequestStatus.SEND);
        reservationRequest.setMessage(clientReservation.getMessage());
        return reservationRequestRepository.save(reservationRequest);
    }

}
