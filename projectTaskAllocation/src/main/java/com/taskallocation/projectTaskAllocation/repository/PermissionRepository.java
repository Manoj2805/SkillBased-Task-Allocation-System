package com.taskallocation.projectTaskAllocation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.entity.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Integer> {
    Permission findByPermissionName(String permissionName);
    boolean existsByPermissionName(String permissionName);
}
