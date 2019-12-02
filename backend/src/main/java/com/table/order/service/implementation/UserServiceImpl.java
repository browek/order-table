package com.table.order.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.table.order.model.security.User;
import com.table.order.model.security.UserCredentials;
import com.table.order.repository.UserRepository;
import com.table.order.service.RoleService;
import com.table.order.service.UserService;


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

    @Override // TODO throw exception if user exist (username must be unique)
    public User save(UserCredentials user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRoles(roleService.getUserRole());
        
        return userRepository.save(newUser);
    }
}

