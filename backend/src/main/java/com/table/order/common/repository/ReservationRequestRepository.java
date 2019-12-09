package com.table.order.common.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.table.order.common.model.ReservationRequest;
import com.table.order.common.model.ReservationRequestStatus;
import com.table.order.common.model.dto.ReservationDTO;

@RepositoryRestResource
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {

    @Query("SELECT r FROM ReservationRequest r WHERE r.client.username = ?#{ authentication?.name }")
    Page<ReservationRequest> findByCurrentClient(Pageable pageable);

    boolean existsByRestaurantOwnerUsernameAndId(String username, Long id);

    @Query("SELECT" +
            " r.id AS id," +
            " r.client.username AS clientUsername," +
            " r.numberOfPersons AS numberOfPersons," +
            " r.reservationDateTime AS reservationDateTime," +
            " r.message AS message" +
            " FROM ReservationRequest r" +
            " WHERE r.restaurant.id = :restaurantId" +
            " AND r.status = :status" +
            " AND r.restaurant.owner.username = :ownerUsername ORDER BY r.reservationDateTime DESC")
    List<ReservationDTO> findAllByRestaurantIdAndStatus(@Param("restaurantId") Integer restaurantId,
                                                        @Param("status") ReservationRequestStatus status,
                                                        @Param("ownerUsername") String ownerUsername);
}
