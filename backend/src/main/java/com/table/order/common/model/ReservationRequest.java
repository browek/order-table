package com.table.order.common.model;

import com.table.order.common.security.model.User;
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
    @JoinColumn(name = "asking_user_id")
    private User askingUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reciving_user_id")
    private User recivingUser;

    private Integer numberOfPersons;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date dateAndTime;
    private String message;

    @Enumerated(EnumType.STRING)
    private ReservationRequestStatus status;

}
