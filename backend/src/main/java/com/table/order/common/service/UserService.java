package com.table.order.common.service;

import com.table.order.common.security.model.User;
import com.table.order.common.security.model.UserCredentials;

public interface UserService {

	void registerClient(UserCredentials user);

	void registerRestaurateur(UserCredentials user);
}
