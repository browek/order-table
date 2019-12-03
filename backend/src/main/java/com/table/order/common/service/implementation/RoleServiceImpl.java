package com.table.order.common.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.table.order.common.security.model.Role;
import com.table.order.common.repository.RoleRepository;
import com.table.order.common.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role getClientRole() {
		return roleRepository.findByName("ROLE_CLIENT");
	}

	@Override
	public Role getRestaurateurRole() {
		return roleRepository.findByName("ROLE_RESTAURATEUR");
	}
}
