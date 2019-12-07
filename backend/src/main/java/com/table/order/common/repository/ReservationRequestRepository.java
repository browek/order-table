package com.table.order.common.repository;

import com.table.order.common.model.ReservationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {

    ReservationRequest findById(Float id);

}
