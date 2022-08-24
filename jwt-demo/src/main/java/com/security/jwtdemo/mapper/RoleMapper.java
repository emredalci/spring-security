package com.security.jwtdemo.mapper;

import com.security.jwtdemo.entity.Role;
import com.security.jwtdemo.model.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleResponse roleToRoleResponse(Role role);

}
