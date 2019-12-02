package com.venues.controller;


import com.venues.model.security.UserCredentials;
import com.venues.repository.UserRepository;
import com.venues.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RepositoryRestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public ResponseEntity<String> saveUser(@RequestBody UserCredentials user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    }

