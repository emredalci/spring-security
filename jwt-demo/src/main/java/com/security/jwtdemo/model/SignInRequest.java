package com.security.jwtdemo.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SignInRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
