package com.table.order.common.service;

import com.table.order.common.repository.NotificationRepository;
import com.table.order.restaurateur.model.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    private NotificationRepository notificationRepository;

    public void sendReservationRecived(Restaurant restaurant) {
        log.error(getClass().getSimpleName() + "sendReservationReceived not implemented yey.");
    }

    @Autowired
    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
}
