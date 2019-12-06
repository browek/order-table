package com.table.order.common.service.implementation;

import java.util.Set;

import com.table.order.common.exceptions.UsernameAlreadyExistsException;
import com.table.order.common.security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.table.order.common.security.model.User;
import com.table.order.common.security.model.UserCredentials;
import com.table.order.common.repository.UserRepository;
import com.table.order.common.service.RoleService;
import com.table.order.common.service.UserService;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    public Set<SimpleGrantedAuthority> getAuthority(User user) {
        SimpleGrantedAuthority userGrantedAuthority = new SimpleGrantedAuthority(user.getRoles().getName());
        
		return Sets.newHashSet(userGrantedAuthority);
    }

    @Override
    public void registerClient(UserCredentials user) {
        checkUsername(user.getUsername());
        registerUser(user, roleService.getClientRole());
    }

    @Override
    public void registerRestaurateur(UserCredentials user) {
        checkUsername(user.getUsername());
        registerUser(user, roleService.getRestaurateurRole());
    }

    private void checkUsername(String username) {
        boolean usernameExists
                = userRepository.existsUserByUsername(username);

        if (usernameExists) throw new UsernameAlreadyExistsException();
    }

    private void registerUser(UserCredentials user, Role role) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRoles(role);

        userRepository.save(newUser);
    }

}

