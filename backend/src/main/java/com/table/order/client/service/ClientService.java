package com.table.order.client.service;


import com.table.order.client.model.NewReservation;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.repository.UserRepository;
import com.table.order.common.security.model.User;
import com.table.order.common.service.ReservationService;
import com.table.order.common.service.helper.UserHelper;
import com.table.order.restaurateur.exception.IncorrectRestaurantDataException;
import com.table.order.restaurateur.model.Restaurant;
import com.table.order.restaurateur.service.RestaurateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RestaurateurService restaurateurService;

    @Autowired
    UserHelper userHelper;

    public ReservationRequest sendReservationRequest(NewReservation newReservation) {
        Restaurant restaurant =
                restaurateurService.getRestaurantByApiId(newReservation.getRestaurantApiId());
        if (restaurant == null) {
            throw new IncorrectRestaurantDataException();
        }

        User client = userHelper.getLoggedUser();
        return reservationService.saveNew(newReservation, client, restaurant);
    }



}
