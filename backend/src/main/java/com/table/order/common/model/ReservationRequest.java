package com.table.order.common.model;

import com.table.order.common.security.model.User;
import com.table.order.restaurateur.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private Integer numberOfPersons;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date reservationDateTime;
    private String message;

    @Enumerated(EnumType.STRING)
    private ReservationRequestStatus status;

}
