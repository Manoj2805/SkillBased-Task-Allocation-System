package com.taskallocation.projectTaskAllocation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskallocation.projectTaskAllocation.dto.RegisterDTO;
import com.taskallocation.projectTaskAllocation.dto.UserDTO;
import com.taskallocation.projectTaskAllocation.dto.UserStatus;
import com.taskallocation.projectTaskAllocation.entity.Role;
import com.taskallocation.projectTaskAllocation.entity.User;
import com.taskallocation.projectTaskAllocation.exception.EmailAlreadyExistsException;
import com.taskallocation.projectTaskAllocation.exception.InvalidCredentialsException;
import com.taskallocation.projectTaskAllocation.mapper.UserMapper;
import com.taskallocation.projectTaskAllocation.repository.RoleRepository;
import com.taskallocation.projectTaskAllocation.repository.UserRepository;

@Service
public class AuthService {
             private final UserRepository userRepository;
	    private final RoleRepository roleRepository;
	    private final UserMapper userMapper;
	    private final BCryptPasswordEncoder passwordEncoder;
        @Autowired
	    public AuthService(UserRepository userRepository,
	                         RoleRepository roleRepository,
	                         UserMapper userMapper,
	                         BCryptPasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.userMapper = userMapper;
	        this.passwordEncoder = passwordEncoder;
	    }
public UserDTO register(RegisterDTO registerDTO) throws EmailAlreadyExistsException {
	    // Check if email already exists
	    if (userRepository.existsByEmail(registerDTO.getEmail())) {
	        throw new EmailAlreadyExistsException("Email already exists.");
	    }

	    // Fetch role from database
	    Optional<Role> optionalRole = roleRepository.findById(registerDTO.getRoleId());
	    if (optionalRole.isEmpty()) {
	        throw new IllegalArgumentException("Invalid role ID: " + registerDTO.getRoleId());
	    }

	    // Map RegisterDTO to User entity
	    User user = new User();
	    user.setName(registerDTO.getName());
	    user.setEmail(registerDTO.getEmail());
	    user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
	    user.setStatus(UserStatus.ACTIVE);
	    user.setRole(optionalRole.get());  // Assign the role

	    // Save user
	    User savedUser = userRepository.save(user);

	    // Map User entity to UserDTO to return
	    UserDTO userDTO = new UserDTO();
	    userDTO.setUserId(savedUser.getUserId());
	    userDTO.setName(savedUser.getName());
	    userDTO.setEmail(savedUser.getEmail());
	    userDTO.setStatus(savedUser.getStatus());
	    userDTO.setRoleId(savedUser.getRole().getRoleId());
	    userDTO.setRoleName(savedUser.getRole().getRoleName());

	    return userDTO;
	}
public UserDTO login(String email, String rawPassword) throws InvalidCredentialsException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

		if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
			throw new InvalidCredentialsException("Invalid email or password");
		}
//		UserDTO dto = userMapper.toDTO(user);
//		return dto;
		
		 try {
		        UserDTO dto = userMapper.toDTO(user);
		        return dto;
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw new RuntimeException("Mapping error: " + e.getMessage());
		    }
		// return userMapper.toDTO(user);
	}
}