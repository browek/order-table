package com.table.order.common.repository;

import com.table.order.common.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.receivedUser.username = :username ORDER BY n.dateAndTime DESC")
    Page<Notification> findAllByReceivedUserUsername(@Param("username") String username, Pageable pageable);

}
