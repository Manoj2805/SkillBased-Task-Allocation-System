package com.taskallocation.projectTaskAllocation.service;

import java.util.List;

import com.taskallocation.projectTaskAllocation.dto.PermissionDTO;
import com.taskallocation.projectTaskAllocation.entity.Permission;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;

public interface PermissionService {
    PermissionDTO createPermission(PermissionDTO permissionDTO) throws UserNotFoundException;
    List<PermissionDTO> getAllPermissions() throws UserNotFoundException;
    PermissionDTO getPermissionById(Integer id) throws UserNotFoundException;
    PermissionDTO updatePermission(Integer id, PermissionDTO permissionDTO) throws UserNotFoundException;
    void deletePermission(Integer id) throws UserNotFoundException;
	Permission getByName(String permissionName) throws UserNotFoundException;
}
