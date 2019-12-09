package com.table.order.common.model.projection;

import java.util.Date;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;
import org.springframework.format.annotation.DateTimeFormat;

import com.table.order.common.model.Notification;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.restaurateur.model.ActivatedRestaurant;

@Projection(name = "withRestaurant", types = ReservationRequest.class)
public interface ReservationRequestWithRestaurant {

	int getId();

	ActivatedRestaurant getRestaurant();

	int getNumberOfPersons();

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
	Date getReservationDateTime();
	
	Set<Notification> getNotifications();
	
	String getMessage();

	ReservationRequestStatus getStatus();

	boolean isActive();
}
