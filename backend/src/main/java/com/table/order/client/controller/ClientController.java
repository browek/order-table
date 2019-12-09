package com.table.order.client.controller;


import com.table.order.client.model.NewReservation;
import com.table.order.client.service.ClientService;
import com.table.order.common.security.exception.UnauthorizedException;
import com.table.order.restaurateur.exception.IncorrectRestaurantDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    @PostMapping("/sendReservationRequest")
    public ResponseEntity<?> sendReservationRequest(@RequestBody NewReservation newReservation) {
        try {
            clientService.sendReservationRequest(newReservation);
            return ResponseEntity.ok().build();
        } catch (IncorrectRestaurantDataException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    @PutMapping("/reservations/accept")
    public ResponseEntity<?> acceptReservationByClient(@RequestParam("reservation_id") Long reservation_id) {
        try {
            clientService.acceptReservationByClient(reservation_id);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    @PutMapping("/reservations/reject")
    public ResponseEntity<?> rejectReservationByClient(@RequestParam("reservation_id") Long reservation_id) {
        try {
            clientService.rejectReservationByClient(reservation_id);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }


}


