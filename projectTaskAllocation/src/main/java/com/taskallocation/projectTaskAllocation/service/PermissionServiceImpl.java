package com.taskallocation.projectTaskAllocation.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskallocation.projectTaskAllocation.dto.PermissionDTO;
import com.taskallocation.projectTaskAllocation.entity.Permission;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	public PermissionDTO createPermission(PermissionDTO dto) throws UserNotFoundException {
		if (permissionRepository.existsByPermissionName(dto.getPermissionName())) {
			throw new UserNotFoundException("Permission already exists with name: " + dto.getPermissionName());
		}

		Permission permission = new Permission();
		permission.setPermissionName(dto.getPermissionName());
		permission.setDescription(dto.getDescription());
		permission.setCreatedAt(LocalDateTime.now());

		Permission saved = permissionRepository.save(permission);

		dto.setPermissionId(saved.getPermissionId());
		return dto;
	}

	public List<PermissionDTO> getAllPermissions() {
		return ((List<Permission>) permissionRepository.findAll()).stream().map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	public PermissionDTO getPermissionById(Integer id) throws UserNotFoundException {
		Permission permission = permissionRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Permission not found with id: " + id));
		return convertToDTO(permission);
	}

//    @Override
	public PermissionDTO updatePermission(Integer id, PermissionDTO dto) throws UserNotFoundException {
		Permission permission = permissionRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Permission not found with id: " + id));

		permission.setPermissionName(dto.getPermissionName());
		permission.setDescription(dto.getDescription());
		permissionRepository.save(permission);

		dto.setPermissionId(permission.getPermissionId());
		return dto;
	}

	public void deletePermission(Integer id) throws UserNotFoundException {
		if (!permissionRepository.existsById(id)) {
			throw new UserNotFoundException("Permission not found with id: " + id);
		}
		permissionRepository.deleteById(id);
	}

	public PermissionDTO convertToDTO(Permission p) {
		PermissionDTO dto = new PermissionDTO();
		dto.setPermissionId(p.getPermissionId());
		dto.setPermissionName(p.getPermissionName());
		dto.setDescription(p.getDescription());
		return dto;
	}
	
	public Permission getByName(String permissionName) {
		return permissionRepository.findByPermissionName(permissionName);
	}
}
