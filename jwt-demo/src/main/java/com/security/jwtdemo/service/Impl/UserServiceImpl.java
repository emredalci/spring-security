package com.security.jwtdemo.service.Impl;

import com.security.jwtdemo.entity.Role;
import com.security.jwtdemo.entity.User;
import com.security.jwtdemo.exception.RoleAlreadyExistException;
import com.security.jwtdemo.exception.RoleNotFoundException;
import com.security.jwtdemo.exception.UserAlreadyExistException;
import com.security.jwtdemo.exception.UserNotFoundException;
import com.security.jwtdemo.mapper.RoleMapper;
import com.security.jwtdemo.mapper.UserMapper;
import com.security.jwtdemo.model.RoleResponse;
import com.security.jwtdemo.model.SignUpRequest;
import com.security.jwtdemo.model.UserResponse;
import com.security.jwtdemo.repository.RoleRepository;
import com.security.jwtdemo.repository.UserRepository;
import com.security.jwtdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserResponse registerUser(SignUpRequest signUpRequest) {
        log.info("New user = {} is saving", signUpRequest.getUsername());
        Boolean isExistUser = userRepository.existsByUsernameOrEmail(signUpRequest.getUsername(), signUpRequest.getEmail());
        if (isExistUser){
            throw new UserAlreadyExistException("Error: Username or email are already taken!");
        }
        User createdUser = User.builder()
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .build();
        User savedUser = userRepository.save(createdUser);
        log.info("New user = {} has just saved", savedUser.getUsername());
        return UserMapper.INSTANCE.userToUserResponse(savedUser);
    }


    @Override
    @Transactional
    public RoleResponse saveRole(String roleType) {
        log.info("New role = {} is saving", roleType);
        Boolean isExistRole = roleRepository.existsByRoleType(roleType);
        if (isExistRole){
            throw new RoleAlreadyExistException("Error: roleType are already taken!");
        }
        Role role = new Role();
        role.setRoleType(roleType);
        Role savedRole = roleRepository.save(role);
        log.info("New role = {} has just saved", roleType);
        return RoleMapper.INSTANCE.roleToRoleResponse(savedRole);
    }

    @Override
    @Transactional
    public void addRoleToUser(String username, String roleType) {
        log.info("New roleType = {} is adding", roleType);
        User user = userRepository.findByUsername(username)
                        .orElseThrow(()-> new UserNotFoundException("Error: user not found with username " + username));
        Role role = roleRepository.findByRoleType(roleType)
                .orElseThrow(()-> new RoleNotFoundException("Error: user not found with username"));
        user.getRoles().add(role);
        userRepository.save(user);
        log.info("New roleType = {} has just added to user = {}", roleType, username);
    }

    @Override
    public UserResponse getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UserNotFoundException("Error: user not found with username " + username);
        }
        return UserMapper.INSTANCE.userToUserResponse(user.get());
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList
                .stream()
                .map(UserMapper.INSTANCE::userToUserResponse)
                .collect(Collectors.toList());
    }
}
