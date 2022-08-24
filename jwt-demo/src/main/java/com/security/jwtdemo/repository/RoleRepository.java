package com.security.jwtdemo.repository;

import com.security.jwtdemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleType(String roleType);

    Boolean existsByRoleType(String roleType);
}
