package com.taskallocation.projectTaskAllocation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.dto.PermissionDTO;
import com.taskallocation.projectTaskAllocation.entity.Permission;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.PermissionService;

@RestController
@RequestMapping("/api/permissions")
@CrossOrigin(origins = "http://localhost:4200")
public class PermissionController {
	// Autowired dependency were added
	@Autowired
	private PermissionService permissionService;
        @PostMapping
	public ResponseEntity<PermissionDTO> createPermission(@RequestBody PermissionDTO permission)
			throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.createPermission(permission));
	}

	@GetMapping
	public List<PermissionDTO> getAllPermissions() throws UserNotFoundException {
		return permissionService.getAllPermissions();
	}
       @GetMapping("/{permissionName}")
	public ResponseEntity<Permission> getPermissionByName(@PathVariable String permissionName)
			throws UserNotFoundException {
		Permission permission = permissionService.getByName(permissionName);
		if (permission == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(permission);
	}
       @PutMapping("/{id}")
	public ResponseEntity<PermissionDTO> updatePermission(@PathVariable Integer id,
			@RequestBody PermissionDTO permissionDTO) throws UserNotFoundException {
		PermissionDTO updated = permissionService.updatePermission(id, permissionDTO);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePermission(@PathVariable Integer id) throws UserNotFoundException {
		permissionService.deletePermission(id);
		return ResponseEntity.noContent().build();
	}
}
