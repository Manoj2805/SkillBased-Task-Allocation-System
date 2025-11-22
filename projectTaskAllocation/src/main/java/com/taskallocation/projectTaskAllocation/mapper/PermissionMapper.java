package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.PermissionDTO;
import com.taskallocation.projectTaskAllocation.entity.Permission;

public class PermissionMapper {

    public static PermissionDTO toDTO(Permission entity) {
        if (entity == null) return null;
        PermissionDTO dto = new PermissionDTO();
        dto.setPermissionId(entity.getPermissionId());
        dto.setPermissionName(entity.getPermissionName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static Permission toEntity(PermissionDTO dto) {
        if (dto == null) return null;
        Permission entity = new Permission();
        entity.setPermissionId(dto.getPermissionId());
        entity.setPermissionName(dto.getPermissionName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
