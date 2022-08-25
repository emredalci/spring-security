package com.security.jwtdemo.controller;

import com.security.jwtdemo.model.RoleResponse;
import com.security.jwtdemo.model.SaveRoleToUserRequest;
import com.security.jwtdemo.model.SignUpRequest;
import com.security.jwtdemo.model.UserResponse;
import com.security.jwtdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user").toUriString());
        return ResponseEntity.created(uri).body(userService.registerUser(signUpRequest));
    }

    @PostMapping("/role/{roleType}")
    public ResponseEntity<RoleResponse> saveRole(@PathVariable String roleType){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/role").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(roleType));
    }

    @PostMapping("/role")
    public ResponseEntity<Void>  saveRoleToUser(@Valid @RequestBody SaveRoleToUserRequest saveRoleToUserRequest){
        userService.addRoleToUser(saveRoleToUserRequest);
        return ResponseEntity.ok().build();
    }

}
