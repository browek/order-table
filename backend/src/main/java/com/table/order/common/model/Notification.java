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
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_user_id")
    private User receivedUser;

    private String title;
    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date dateAndTime;

    private boolean displayed;
}
