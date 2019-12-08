package com.table.order.restaurateur.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.table.order.common.model.Activated;
import com.table.order.common.model.ReservationRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "restaurant")
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("1")
@Where(clause = "active = 1")
public class ActivatedRestaurant extends Restaurant implements Activated {

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private Set<ReservationRequest> reservationRequests;
	
	private boolean active = true;

	@Override
	public boolean isActive() {
		return this.active;
	}

	@Override
	public void setActive(boolean value) {
		this.active = value;
	}

}
