package com.taskallocation.projectTaskAllocation.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskallocation.projectTaskAllocation.dto.RoleDTO;
import com.taskallocation.projectTaskAllocation.entity.Role;
import com.taskallocation.projectTaskAllocation.entity.User;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public RoleDTO createRole(RoleDTO roleDTO) throws UserNotFoundException{
        if (roleRepository.existsByRoleName(roleDTO.getRoleName())) {
            throw new  UserNotFoundException("Role already exists with name: " + roleDTO.getRoleName());
        }
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());
        role.setDescription(roleDTO.getDescription());
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        Role saved = roleRepository.save(role);
        return mapToDTO(saved);
    }

      @Override
    public RoleDTO updateRole(Integer roleId, RoleDTO roleDTO) throws UserNotFoundException{
        Role role = roleRepository.findById(roleId).orElseThrow(() ->
                new  UserNotFoundException("Role is not found with ID:" + roleId));
        role.setRoleName(roleDTO.getRoleName());
        role.setDescription(roleDTO.getDescription());
        role.setUpdatedAt(LocalDateTime.now());

        return mapToDTO(roleRepository.save(role));
    }

    @Override
    public void deleteRole(Integer roleId) throws UserNotFoundException{
        if (!roleRepository.existsById(roleId)) {
            throw new  UserNotFoundException("Role not found with ID: " + roleId);
        }
        roleRepository.deleteById(roleId);
    }

    public List<RoleDTO> getAllRoles() {
        return ((List<Role>) roleRepository.findAll())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
   
private RoleDTO convertToDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(role.getRoleId());
        dto.setRoleName(role.getRoleName());
        dto.setDescription(role.getDescription());

        if (role.getUsers() != null) {
            dto.setUserNames(role.getUsers().stream()
                    .map(User::getName)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
     @Override
    public RoleDTO getRoleById(Integer roleId) throws UserNotFoundException{
        Role role = roleRepository.findById(roleId).orElseThrow(() ->
                new UserNotFoundException("Role not found with ID: " + roleId));
        return mapToDTO(role);
    }

    private RoleDTO mapToDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(role.getRoleId());
        dto.setRoleName(role.getRoleName());
        dto.setDescription(role.getDescription());
        return dto;
    }
}