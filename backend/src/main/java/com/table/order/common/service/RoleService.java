package com.table.order.common.service;

import com.table.order.common.security.model.Role;

public interface RoleService {
	Role getClientRole();

	Role getRestaurateurRole();
}
