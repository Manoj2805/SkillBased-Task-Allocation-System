package com.taskallocation.projectTaskAllocation.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.dto.RoleDTO;
import com.taskallocation.projectTaskAllocation.entity.Role;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.RoleRepository;
import com.taskallocation.projectTaskAllocation.repository.UserRepository;
import com.taskallocation.projectTaskAllocation.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
        @PostMapping
	// problem in the post unique value
	public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) throws UserNotFoundException {
		return ResponseEntity.ok(roleService.createRole(roleDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoleDTO> updateRole(@PathVariable("id") Integer id, @RequestBody RoleDTO roleDTO)
			throws UserNotFoundException {
		return ResponseEntity.ok(roleService.updateRole(id, roleDTO));
	}
@DeleteMapping("/{id}")
	public String deleteRole(@PathVariable Integer id) throws UserNotFoundException {
		roleService.deleteRole(id);
		return "Successfully Deleted the row ";
	}
@GetMapping
	public ResponseEntity<List<RoleDTO>> getAllRoles() throws UserNotFoundException {
		return ResponseEntity.ok(roleService.getAllRoles());
	}

	@GetMapping("/roles-with-user-count")
	public List<RoleDTO> getRolesWithUserCount() {
		List<Role> roles = (List<Role>) roleRepository.findAll();
		return roles.stream().map(role -> {
			RoleDTO dto = new RoleDTO();
			dto.setRoleId(role.getRoleId());
			dto.setRoleName(role.getRoleName());
			dto.setDescription(role.getDescription());
			dto.setUserCount(userRepository.countByRole_RoleId(role.getRoleId())); // 👈
			return dto;
		}).collect(Collectors.toList());
	}
@GetMapping("/{id}")
	public ResponseEntity<RoleDTO> getRoleById(@PathVariable("id") Integer id) throws UserNotFoundException {
		return ResponseEntity.ok(roleService.getRoleById(id));
	}
}
