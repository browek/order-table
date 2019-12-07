package com.table.order.common.security.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.table.order.common.model.EventMessage;
import com.table.order.common.model.ReservationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	private String restaurantApiId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_role")
	private Role roles;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "askingUser")
	private List<ReservationRequest> sendReservationRequests;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recivingUser")
	private List<ReservationRequest> receivedReservationRequests;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<EventMessage> eventMessages;
}
