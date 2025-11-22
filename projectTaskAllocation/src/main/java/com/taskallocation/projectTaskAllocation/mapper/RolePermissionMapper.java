package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.RolePermissionDTO;
import com.taskallocation.projectTaskAllocation.entity.RolePermission;

public class RolePermissionMapper {

    // Convert from RolePermission entity to RolePermissionDTO
    public static RolePermissionDTO toDTO(RolePermission entity) {
        if (entity == null) return null;
        RolePermissionDTO dto = new RolePermissionDTO();
//        dto.setRoleId(entity.getRoleId());
//        dto.setPermissionId(entity.getPermissionId());
        dto.setGrantedBy(entity.getGrantedBy());
        dto.setGrantedAt(entity.getGrantedAt());
        return dto;
    }

    // Convert from RolePermissionDTO to RolePermission entity
    public static RolePermission toEntity(RolePermissionDTO dto) {
        if (dto == null) return null;
        RolePermission entity = new RolePermission();
//        entity.setRoleId(dto.getRoleId());
//        entity.setPermissionId(dto.getPermissionId());
        entity.setGrantedBy(dto.getGrantedBy());
        entity.setGrantedAt(dto.getGrantedAt());
        return entity;
    }
}