package com.table.order.common.security.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.table.order.common.model.Notification;
import com.table.order.common.model.ReservationRequest;
import com.table.order.restaurateur.model.Restaurant;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Long id;
	
	@NonNull
	private String username;

	@JsonIgnore
	@NonNull
	private String password;
	
	private boolean enabled;

	@JsonIgnore
	@ManyToOne
	@NonNull
	@JoinColumn(name = "id_role")
	private Role roles;
	
	@OneToMany(mappedBy = "owner")
	private Set<Restaurant> restaurants;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	private Set<ReservationRequest> sendReservationRequests;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receivedUser")
	private Set<Notification> notifications;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}
