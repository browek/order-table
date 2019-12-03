package com.table.order.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.table.order.common.security.model.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	List<User> findAll();

	boolean existsUserByUsername(String username);
}
