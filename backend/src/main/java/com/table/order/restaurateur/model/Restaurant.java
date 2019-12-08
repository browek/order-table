package com.table.order.restaurateur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.table.order.common.security.model.User;

import lombok.Data;

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
	
}
