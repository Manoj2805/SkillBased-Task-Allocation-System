package com.taskallocation.projectTaskAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.entity.RolePermission;
import com.taskallocation.projectTaskAllocation.entity.RolePermissionId;

@Repository
public interface RolePermissionRepository extends CrudRepository<RolePermission, RolePermissionId> {
    RolePermission findByIdRoleIdAndIdPermissionId(Integer roleId, Integer permissionId);
    List<RolePermission> findByIdRoleId(Integer roleId);
}