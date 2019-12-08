package com.table.order.restaurateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.table.order.restaurant.model.Restaurant;

@RepositoryRestResource
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	
	Restaurant findByApiId(@Param("apiId") String apiId);
}
