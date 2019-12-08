package com.table.order.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.table.order.common.model.ReservationRequest;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {

	@Query("SELECT R FROM ReservationRequest R WHERE R.id =:id")
	ReservationRequest findByLongId(@Param("id") Long id);

	@Override
	@Query("SELECT r FROM ReservationRequest r WHERE r.client.username = ?#{ authentication?.name }")
	Page<ReservationRequest> findAll(Pageable pageable);

}
