package com.table.order.restaurateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.table.order.restaurateur.model.Restaurant;

@RepositoryRestResource(exported = false)
public interface RestaurantRepository  extends JpaRepository<Restaurant, Integer> {
	
	Restaurant findByApiId(String apiId);

    boolean existsByApiId(String apiId);
}
