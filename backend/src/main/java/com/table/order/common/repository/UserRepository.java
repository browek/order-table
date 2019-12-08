package com.table.order.common.repository;

import com.table.order.common.security.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> findAll();

    boolean existsUserByUsername(String username);

    @Modifying
    @Query("UPDATE User U SET U.restaurantApiId = :restaurantApiId WHERE U.username = :username")
    void updateRestarantIdByUsername(@Param("username") String username,
                                     @Param("restaurantApiId") String restaurantApiId);

    @Query("SELECT U.restaurantApiId FROM User U WHERE U.username = :username")
    String findRestaurantIdByUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE User U SET U.restaurantApiId = NULL WHERE U.username = :username")
    void deleteRestaurantIdFromUser(@Param("username") String username);

    @Query("SELECT U FROM User U WHERE U.restaurantApiId =:apiId")
    User findUserIdByRestaurantId(@Param("apiId") String apiId);

    @Query("SELECT U.restaurantApiId FROM User U WHERE U.restaurantApiId = :restaurantId")
    String findRestaurantIdIfExists(@Param("restaurantId") String restaurantId);
}
