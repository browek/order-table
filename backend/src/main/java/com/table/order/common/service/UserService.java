package com.table.order.common.service;

import com.google.common.collect.Sets;
import com.table.order.common.exceptions.UsernameAlreadyExistsException;
import com.table.order.common.repository.UserRepository;
import com.table.order.common.security.exception.UnauthorizedException;
import com.table.order.common.security.model.Role;
import com.table.order.common.security.model.User;
import com.table.order.common.security.model.UserCredentials;
import com.table.order.common.service.helper.UserHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Qualifier("userService")
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder bcryptEncoder;
    private UserHelper userHelper;

    public UserService(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder bcryptEncoder, UserHelper userHelper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bcryptEncoder = bcryptEncoder;
        this.userHelper = userHelper;
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

    public void registerClient(UserCredentials user) {
        checkUsername(user.getUsername());
        registerUser(user, roleService.getClientRole());
    }

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

    @Transactional
    public void assignRestaurantToLoggedUser(String restaurantApiId) throws UnauthorizedException {
        if (!userHelper.isLoggedRestaurateur())
            throw new UnauthorizedException();

        userRepository.updateRestarantIdByUsername(
                userHelper.getLoggedUserUsername(),
                restaurantApiId
        );
    }

    public String getRestaurantIdOfLoggedUser() {
        if (!userHelper.isLoggedRestaurateur())
            return null;

        return userRepository.findRestaurantIdByUsername(userHelper.getLoggedUserUsername());
    }

    @Transactional
    public void removeRestaurantFromLoggedUser() throws UnauthorizedException {
        if (!userHelper.isLoggedRestaurateur())
            throw new UnauthorizedException();

        userRepository.deleteRestaurantIdFromUser(userHelper.getLoggedUserUsername());
    }
}

