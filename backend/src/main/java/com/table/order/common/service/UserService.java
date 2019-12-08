package com.table.order.common.service;

import com.google.common.collect.Sets;
import com.table.order.client.service.ClientService;
import com.table.order.common.exceptions.UsernameAlreadyExistsException;
import com.table.order.common.repository.UserRepository;
import com.table.order.common.security.model.Role;
import com.table.order.common.security.model.User;
import com.table.order.common.security.model.UserCredentials;
import com.table.order.restaurateur.service.RestaurateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    RestaurateurService restaurateurService;
    @Autowired
    ClientService clientService;
    private UserRepository userRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder bcryptEncoder;


    public UserService(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bcryptEncoder = bcryptEncoder;
    }

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

    public User registerClient(UserCredentials user) {
        checkUsername(user.getUsername());

        return registerUser(user, roleService.getClientRole());
    }

    public User registerRestaurateur(UserCredentials user) {
        checkUsername(user.getUsername());

        return registerUser(user, roleService.getRestaurateurRole());
    }

    private void checkUsername(String username) {
        boolean usernameExists
                = userRepository.existsUserByUsername(username);

        if (usernameExists) throw new UsernameAlreadyExistsException();
    }

    private User registerUser(UserCredentials user, Role role) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRoles(role);
        newUser.setEnabled(true);

        return userRepository.save(newUser);
    }

    public User deleteUser(Long id) {
        User user = userRepository.findByLongId(id);
        if (!user.isActive()) {
            throw new UsernameNotFoundException("User does not exist");
        }
        user.setActive(false);
        user.getRestaurants().stream().map(restaurant -> restaurateurService.deleteRestaurant(restaurant)).collect(Collectors.toList());
        user.getSendReservationRequests().stream().map(reservation -> clientService.deleteReservation(reservation)).collect(Collectors.toList());
        return userRepository.save(user);
    }


}

