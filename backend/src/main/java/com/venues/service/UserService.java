package com.venues.service;


import com.venues.model.security.User;
import com.venues.model.security.UserCredentials;


public interface UserService {

    User save(UserCredentials user);
}
