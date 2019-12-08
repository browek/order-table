package com.table.order.client.controller;


import com.table.order.client.model.NewReservation;
import com.table.order.client.service.ClientService;
import com.table.order.restaurateur.exception.IncorrectRestaurantDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}


