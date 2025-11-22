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

import com.taskallocation.projectTaskAllocation.dto.UserDTO;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true") 
public class UserController {

    private static final String MANAGER_ID = "managerId";
	@Autowired
    private UserService userService;
	
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws UserNotFoundException{
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }
@PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Integer userId, @Valid @RequestBody UserDTO userDTO) throws UserNotFoundException{
        return ResponseEntity.ok(userService.updateUser(userId, userDTO));
    }

    @DeleteMapping("/{id}")//it is difficult to delete parent while child exists
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer userId) throws UserNotFoundException{
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer userId) throws UserNotFoundException{
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() throws UserNotFoundException{
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<UserDTO>> getUsersByRole(@PathVariable Integer roleId) throws UserNotFoundException{
        List<UserDTO> users = userService.getUsersByRole(roleId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<UserDTO>> getUsersByManager(@PathVariable(MANAGER_ID) Integer managerId) throws UserNotFoundException{
        return ResponseEntity.ok(userService.getUsersByManager(managerId));
    }
}
