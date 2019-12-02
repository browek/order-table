package com.venues.repository;

import com.venues.model.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users" , path = "users")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    void deleteById(Long id);
    @PreAuthorize("hasRole('ROLE_USER')")
    List<User> findAll();
    boolean existsUserByUsername(String username);
   public User save(User user);
}
