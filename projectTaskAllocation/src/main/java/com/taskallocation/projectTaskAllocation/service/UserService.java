package com.taskallocation.projectTaskAllocation.service;

import java.util.List;

import com.taskallocation.projectTaskAllocation.dto.UserDTO;
import com.taskallocation.projectTaskAllocation.entity.User;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;

public interface UserService {
    UserDTO createUser(UserDTO userDTO) throws UserNotFoundException;
    UserDTO updateUser(Integer userId, UserDTO userDTO) throws UserNotFoundException;
    User deleteUser(Integer userId) throws UserNotFoundException;
    UserDTO getUserById(Integer userId) throws UserNotFoundException;
    List<UserDTO> getAllUsers() throws UserNotFoundException;
    List<UserDTO> getUsersByRole(Integer roleId) throws UserNotFoundException;
    List<UserDTO> getUsersByManager(Integer managerId) throws UserNotFoundException;
}
