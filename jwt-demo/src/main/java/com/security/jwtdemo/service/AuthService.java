package com.security.jwtdemo.service;

import com.security.jwtdemo.model.JwtResponse;
import com.security.jwtdemo.model.SignInRequest;
import com.security.jwtdemo.model.SignUpRequest;
import com.security.jwtdemo.model.UserResponse;

public interface AuthService {

    JwtResponse authenticateUser(SignInRequest signInRequest);
}
