package com.table.order.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.table.order.model.security.Role;
import com.table.order.repository.RoleRepository;
import com.table.order.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role getUserRole() {
		return roleRepository.findByName("ROLE_USER");
	}
}
