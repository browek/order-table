package com.table.order.common.repository;

import com.table.order.common.security.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> findAll();

    boolean existsUserByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.enabled = 0 WHERE user.id = :userId")
    public int disableUserAccount(@Param("userId") Long id);

    @Query("SELECT U FROM User U WHERE U.id =:id")
    User findByLongId(@Param("id") Long id);


}
