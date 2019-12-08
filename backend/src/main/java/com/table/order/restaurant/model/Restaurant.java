package com.table.order.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.table.order.common.security.model.User;

import lombok.Data;

@Entity
@Data
public class Restaurant {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(unique = true)
	private String apiId;
	
	private String name;
	
	private String city;
	
	private String street;
	
	@NotNull
	@ManyToOne(optional = false)
	private User owner;
	
}
