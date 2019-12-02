package com.table.order.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.table.order.model.security.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

	@PreAuthorize("hasRole('ROLE_USER')")
	List<User> findAll();

	boolean existsUserByUsername(String username);
}
