package com.table.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.table.order.model.security.UserCredentials;
import com.table.order.service.UserService;

@RepositoryRestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/users/signup")
	public ResponseEntity<String> saveUser(@RequestBody UserCredentials user) {
		userService.save(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
