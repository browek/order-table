package com.table.order.common.service;

import com.table.order.common.repository.RoleRepository;
import com.table.order.common.security.model.Role;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleService {

	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role getClientRole() {
		return roleRepository.findByName("ROLE_CLIENT");
	}

	public Role getRestaurateurRole() {
		return roleRepository.findByName("ROLE_RESTAURATEUR");
	}
}
