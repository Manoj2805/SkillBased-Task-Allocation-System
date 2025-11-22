package com.taskallocation.projectTaskAllocation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	 Optional<User> findByEmail(String email);
    List<User> findByRole_RoleId(Integer roleId);
    List<User> findByManager_UserId(Integer managerId);
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.email = :email")
    Optional<User> findByEmailWithRole(@Param("email") String email);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.role.roleId = :roleId")
    Long countByRole_RoleId(@Param("roleId") Integer roleId);

}