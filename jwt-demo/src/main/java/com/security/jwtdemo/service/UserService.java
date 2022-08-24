package com.security.jwtdemo.service;

import com.security.jwtdemo.entity.Role;
import com.security.jwtdemo.model.RoleResponse;
import com.security.jwtdemo.model.SignUpRequest;
import com.security.jwtdemo.model.UserResponse;

import java.util.List;

public interface UserService {

    RoleResponse saveRole(String roleType);
    UserResponse registerUser(SignUpRequest signUpRequest);
    void addRoleToUser(String username, String roleType);
    UserResponse getUser(String username);
    List<UserResponse> getAllUsers();
}
