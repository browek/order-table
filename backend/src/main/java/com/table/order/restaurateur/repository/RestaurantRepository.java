package com.table.order.restaurateur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.table.order.restaurateur.model.Restaurant;

@RepositoryRestResource
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	
	Restaurant findByApiId(@Param("apiId") String apiId);

    boolean existsByApiId(String apiId);
    
    @Override
    @Query("SELECT r FROM Restaurant r WHERE r.owner.username = ?#{ authentication.username }")
    List<Restaurant> findAll();
}
