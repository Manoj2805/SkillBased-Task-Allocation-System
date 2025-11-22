package com.taskallocation.projectTaskAllocation.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.taskallocation.projectTaskAllocation.dto.UserDTO;
import com.taskallocation.projectTaskAllocation.entity.Role;
import com.taskallocation.projectTaskAllocation.entity.User;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.RoleRepository;
import com.taskallocation.projectTaskAllocation.repository.UserRepository;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
         @Override
	public UserDTO createUser(UserDTO userDTO) throws UserNotFoundException {
		User user = mapToEntity(userDTO);
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		// user.setCreatedBy(userDTO.getCreatedBy());
//        user.setAddress(userDTO.getAddress());
//        user.setName(userDTO.getName());
//        user.setStatus(userDTO.getStatus());
//        user.setEmail(userDTO.getEmail());
		if (userDTO.getRoleId() != null) {
			Role role = roleRepository.findById(userDTO.getRoleId())
					.orElseThrow(() -> new UserNotFoundException("Role not found"));
			// Role role=new Role();
			user.setRole(role);
		}

		if (userDTO.getManagerId() != null) {
			User manager = userRepository.findById(userDTO.getManagerId())
					.orElseThrow(() -> new RuntimeException("Manager not found"));
			user.setManager(manager);
		}

		userRepository.save(user);
		return mapToDTO(user);
	}
        @Override
	public UserDTO updateUser(Integer userId, UserDTO userDTO) throws UserNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setAddress(userDTO.getAddress());
		user.setStatus(userDTO.getStatus());
		user.setUpdatedAt(LocalDateTime.now());
		user.setPassword(userDTO.getPassword());
		user.setUpdatedBy(userDTO.getUpdatedBy());

		if (userDTO.getRoleId() != null) {
			Role role = roleRepository.findById(userDTO.getRoleId())
					.orElseThrow(() -> new RuntimeException("Role not found"));
			user.setRole(role);
		}

		if (userDTO.getManagerId() != null) {
			User manager = userRepository.findById(userDTO.getManagerId())
					.orElseThrow(() -> new RuntimeException("Manager not found"));
			user.setManager(manager);
		} else {
			user.setManager(null);
		}

		userRepository.save(user);
		return mapToDTO(user);
	}
    @Override
	public User deleteUser(Integer userId) throws UserNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		List<User> subordinates = userRepository.findByManager_UserId(userId);
		if (!subordinates.isEmpty()) {
			throw new IllegalStateException("Cannot delete user. They manage other users.");
		}
		userRepository.delete(user);
		return user;
	}
     @Override
	public UserDTO getUserById(Integer userId) throws UserNotFoundException {
		return userRepository.findById(userId).map(this::mapToDTO)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
	}

	@Override
	public List<UserDTO> getAllUsers() throws UserNotFoundException {
		return ((List<User>) userRepository.findAll()).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> getUsersByRole(Integer roleId) throws UserNotFoundException {
		return userRepository.findByRole_RoleId(roleId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> getUsersByManager(Integer managerId) throws UserNotFoundException {
		return userRepository.findByManager_UserId(managerId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
       private UserDTO mapToDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setUserId(user.getUserId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPhone(user.getPhone());
		dto.setPassword(user.getPassword());
		dto.setAddress(user.getAddress());
		dto.setStatus(user.getStatus());
		// dto.setCreatedBy(user.getCreatedBy());
		dto.setRoleId(user.getRole() != null ? user.getRole().getRoleId() : null);
		dto.setManagerId(user.getManager() != null ? user.getManager().getUserId() : null);
		return dto;
	}
      private User mapToEntity(UserDTO dto) {
		User user = new User();
		user.setUserId(dto.getUserId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());
		user.setAddress(dto.getAddress());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setStatus(dto.getStatus());
		// user.setCreatedBy(dto.getCreatedBy());
		return user;
	}
}