package com.security.jwtdemo.repository;

import com.security.jwtdemo.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsernameOrEmail(String username, String email);
    Boolean existsByEmail(String email);

    User getById(Long id);

}
