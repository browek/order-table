package com.table.order.common.repository;

import com.table.order.common.model.ReservationRequest;
import com.table.order.common.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {

    @Query("SELECT R FROM ReservationRequest R WHERE R.id =:id")
    ReservationRequest findByLongId(@Param("id") Long id);



}
