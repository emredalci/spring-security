package com.security.jwtdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveRoleToUserRequest {

    @NotBlank
    private String roleType;
    @NotBlank
    private String username;
}
