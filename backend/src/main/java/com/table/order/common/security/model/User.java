package com.table.order.common.security.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.table.order.common.model.EventMessage;
import com.table.order.common.model.ReservationRequest;

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

	@NonNull
	private String restaurantApiId;
	
	private boolean enabled;

	@JsonIgnore
	@ManyToOne
	@NonNull
	@JoinColumn(name = "id_role")
	private Role roles;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "askingUser")
	private List<ReservationRequest> sendReservationRequests;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recivingUser")
	private List<ReservationRequest> receivedReservationRequests;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<EventMessage> eventMessages;
}
