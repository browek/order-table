package com.table.order.service;

import com.table.order.model.security.User;
import com.table.order.model.security.UserCredentials;

public interface UserService {

	User save(UserCredentials user);
}
