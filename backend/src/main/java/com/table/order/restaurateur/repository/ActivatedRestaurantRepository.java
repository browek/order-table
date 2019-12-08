package com.table.order.restaurateur.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.table.order.restaurateur.model.ActivatedRestaurant;

@RepositoryRestResource(collectionResourceRel = "restaurants", path = "restaurants")
public interface ActivatedRestaurantRepository extends JpaRepository<ActivatedRestaurant, Integer> {
	
	ActivatedRestaurant findByApiId(@Param("apiId") String apiId);

    boolean existsByApiId(String apiId);
    
    @Override
    @Query("SELECT r FROM Restaurant r WHERE r.owner.username = ?#{ authentication?.name }")
    Page<ActivatedRestaurant> findAll(Pageable pageable);
}
