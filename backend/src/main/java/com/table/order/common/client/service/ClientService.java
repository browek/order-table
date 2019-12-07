package com.table.order.common.client.service;


import com.table.order.common.client.model.ClientReservation;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.common.repository.ReservationRequestRepository;
import com.table.order.common.repository.UserRepository;
import com.table.order.common.security.model.User;
import com.table.order.restaurateur.exception.IncorrectRestaurantDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClientService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRequestRepository reservationRequestRepository;

    public ReservationRequest sendReservationRequest(ClientReservation clientReservation) throws IncorrectRestaurantDataException {
        User user = userRepository.findUserIdByRestaurantId(clientReservation.getApiId());
        if (user == null) {
            throw new IncorrectRestaurantDataException();
        }
        return createNewReservation(clientReservation, user);
    }

    private ReservationRequest createNewReservation(ClientReservation clientReservation, User user) {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setAskingUser(getCurrentUser());
        reservationRequest.setRecivingUser(user);
        reservationRequest.setDateAndTime(clientReservation.getDateAndTime());
        reservationRequest.setNumberOfPersons(clientReservation.getNumberOfPersons());
        reservationRequest.setStatus(ReservationRequestStatus.SEND);
       return reservationRequestRepository.save(reservationRequest);
    }

    private User getCurrentUser(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username);
        return user;
    }



}
