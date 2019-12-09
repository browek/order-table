package com.table.order.client.service;


import com.table.order.client.model.NewReservation;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.common.repository.ReservationRequestRepository;
import com.table.order.common.repository.UserRepository;
import com.table.order.common.security.exception.UnauthorizedException;
import com.table.order.common.security.model.User;
import com.table.order.common.service.ReservationService;
import com.table.order.common.service.helper.UserHelper;
import com.table.order.restaurateur.exception.IncorrectRestaurantDataException;
import com.table.order.restaurateur.model.ActivatedRestaurant;
import com.table.order.restaurateur.service.RestaurateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserHelper userHelper;
    @Autowired
    ReservationRequestRepository reservationRequestRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RestaurateurService restaurateurService;

    public ReservationRequest sendReservationRequest(NewReservation newReservation) {
        ActivatedRestaurant restaurant =
                restaurateurService.getRestaurantByApiId(newReservation.getRestaurantApiId());
        if (restaurant == null) {
            throw new IncorrectRestaurantDataException();
        }

        User client = userHelper.getLoggedUser();
        return reservationService.saveNew(newReservation, client, restaurant);
    }

    public ReservationRequest deleteReservation(ReservationRequest reservationRequest) {
        reservationRequest.setActive(false);
        return reservationRequestRepository.save(reservationRequest);
    }

    public ReservationRequest acceptReservationByClient(Long reservation_id) throws UnauthorizedException {
        ReservationRequest reservation = reservationRequestRepository.getOne(reservation_id);
        checkReservationOwner(reservation);
        reservation.setStatus(ReservationRequestStatus.ACCEPTED_BY_CLIENT);
        return reservationRequestRepository.save(reservation);
    }

    public ReservationRequest rejectReservationByClient(Long reservation_id) throws UnauthorizedException {
        ReservationRequest reservation = reservationRequestRepository.getOne(reservation_id);
        checkReservationOwner(reservation);
        reservation.setStatus(ReservationRequestStatus.REJECTED_BY_CLIENT);
        return reservationRequestRepository.save(reservation);
    }

    private void checkReservationOwner(ReservationRequest reservation) throws UnauthorizedException {
        String loggedUserUsername = userHelper.getLoggedUserUsername();
        if (!reservation.getClient().getUsername().equals(loggedUserUsername))
            throw new UnauthorizedException();
    }


}
