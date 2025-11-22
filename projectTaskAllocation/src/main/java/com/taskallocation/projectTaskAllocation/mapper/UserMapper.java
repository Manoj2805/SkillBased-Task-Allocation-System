package com.taskallocation.projectTaskAllocation.mapper;

import org.springframework.stereotype.Component;

import com.taskallocation.projectTaskAllocation.dto.UserDTO;
import com.taskallocation.projectTaskAllocation.entity.Role;
import com.taskallocation.projectTaskAllocation.entity.User;

@Component
public class UserMapper {

	public User toEntity(UserDTO dto) {
		User user = new User();
		user.setUserId(dto.getUserId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());
		user.setAddress(dto.getAddress());
		user.setStatus(dto.getStatus());
		user.setUpdatedBy(dto.getUpdatedBy());

		if (dto.getRoleId() != null) {
			Role role = new Role();
			role.setRoleId(dto.getRoleId());
			user.setRole(role);
		}

		if (dto.getManagerId() != null) {
			User manager = new User();
			manager.setUserId(dto.getManagerId());
			user.setManager(manager);
		}

		return user;
	}
public UserDTO toDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setUserId(user.getUserId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPhone(user.getPhone());
		dto.setAddress(user.getAddress());
		dto.setStatus(user.getStatus());
		dto.setUpdatedBy(user.getUpdatedBy());

		if (user.getRole() != null) {
			dto.setRoleId(user.getRole().getRoleId());
		    dto.setRoleName(user.getRole().getRoleName());
		}
		if (user.getManager() != null)
			dto.setManagerId(user.getManager().getUserId());

		return dto;
	}
}