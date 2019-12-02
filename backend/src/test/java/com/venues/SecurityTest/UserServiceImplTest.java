package com.venues.SecurityTest;

import com.venues.model.security.Role;
import com.venues.model.security.User;
import com.venues.model.security.UserCredentials;
import com.venues.repository.UserRepository;
import com.venues.service.RoleService;
import com.venues.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder bcryptEncoder;

    @Mock
    RoleService roleService;

    @InjectMocks
    UserServiceImpl userService;


    User user;
    UserCredentials userCredentials;
    Role role;
    String password;
    User user1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        role = new Role(3L, "ROLE_USER", "User Role");
        user = new User(1L, "name", "password", role);
        userCredentials = new UserCredentials("name", "pd");
        user1 = new User();
    }

    @Test
    public void saveUser_Test() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User insertedUser = userService.save(userCredentials);

        assertAll(
                () -> assertNotNull(insertedUser),
                () -> assertEquals(user, insertedUser)
        );
    }

    @Test
    public void saveUserFail_Test() {
        when(userRepository.save(any(User.class))).thenReturn(null);

        assertNull(userService.save(userCredentials));
    }

    @Test
    public void getAuthority_Test() {
        assertAll(
                () -> assertNotNull(userService.getAuthority(user)),
                () -> assertEquals(user.getRoles().getName(), "ROLE_USER")
        );
    }

    @Test
    public void getAuthorityFailUserWithoutRole_Test() {
        assertThrows(NullPointerException.class, () -> {
            userService.getAuthority(user1);
        });
    }

    @Test
    public void loadUser_Test() {
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        assertNotNull(userService.loadUserByUsername("exampleUsername"));
    }

    @Test
    public void loadUserFail_Test() {
        when(userRepository.findByUsername(anyString())).thenReturn(user1);

        assertThrows(NullPointerException.class, () -> {
            userService.loadUserByUsername("exampleUsername");
        });
    }
}
