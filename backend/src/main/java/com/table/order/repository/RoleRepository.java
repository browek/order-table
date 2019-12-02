package com.table.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.table.order.model.security.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByName(String name);
}
