package com.table.order.client.service;


import com.table.order.client.model.NewReservation;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.common.repository.ReservationRequestRepository;
import com.table.order.common.repository.UserRepository;
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

    public ReservationRequest sendReservationRequest(NewReservation newReservation) {
        Restaurant restaurant = restaurantRepository.findByApiId(newReservation.getRestaurantApiId());
        if (restaurant == null) {
            throw new IncorrectRestaurantDataException();
        }
        return createNewReservation(newReservation, restaurant);
    }

    private ReservationRequest createNewReservation(NewReservation newReservation, Restaurant restaurant) {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setClient(userHelper.getLoggedUser());
        reservationRequest.setRestaurant(restaurant);
        reservationRequest.setReservationDateTime(newReservation.getDateAndTime());
        reservationRequest.setNumberOfPersons(newReservation.getNumberOfPersons());
        reservationRequest.setStatus(ReservationRequestStatus.SEND);
        reservationRequest.setMessage(validateMessage(newReservation.getMessage()));
        return reservationRequestRepository.save(reservationRequest);
    }

    private String validateMessage(String message) {
        return message.trim().isEmpty() ? null : message.trim();
    }

}
