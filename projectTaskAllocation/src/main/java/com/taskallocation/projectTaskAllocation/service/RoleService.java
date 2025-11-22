package com.taskallocation.projectTaskAllocation.service;

import java.util.List;

import com.taskallocation.projectTaskAllocation.dto.RoleDTO;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO) throws UserNotFoundException;
    RoleDTO updateRole(Integer roleId, RoleDTO roleDTO) throws UserNotFoundException;
    void deleteRole(Integer roleId) throws UserNotFoundException;
    List<RoleDTO> getAllRoles() throws UserNotFoundException;
    RoleDTO getRoleById(Integer roleId) throws UserNotFoundException;
}

