package com.table.order.restaurateur.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.table.order.common.model.ReservationRequest;
import com.table.order.common.security.model.User;

import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Restaurant {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(unique = true, name = "api_id")
	private String apiId;
	
	private String name;
	
	private String city;
	
	private String street;
	
	@NotNull
	@ManyToOne(optional = false)
	@JsonIgnore
	private User owner;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private Set<ReservationRequest> reservationRequests;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Restaurant that = (Restaurant) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}
