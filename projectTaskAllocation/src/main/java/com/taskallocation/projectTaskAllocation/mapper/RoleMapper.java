package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.RoleDTO;
import com.taskallocation.projectTaskAllocation.entity.Role;

public class RoleMapper {

    public static RoleDTO toDTO(Role entity) {
        if (entity == null) return null;
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setRoleName(entity.getRoleName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static Role toEntity(RoleDTO dto) {
        if (dto == null) return null;
        Role entity = new Role();
        entity.setRoleId(dto.getRoleId());
        entity.setRoleName(dto.getRoleName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
