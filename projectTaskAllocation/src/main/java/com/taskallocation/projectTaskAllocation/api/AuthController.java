package com.taskallocation.projectTaskAllocation.api;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.dto.LoginRequest;
import com.taskallocation.projectTaskAllocation.dto.RegisterDTO;
import com.taskallocation.projectTaskAllocation.dto.UserDTO;
import com.taskallocation.projectTaskAllocation.exception.EmailAlreadyExistsException;
import com.taskallocation.projectTaskAllocation.exception.InvalidCredentialsException;
import com.taskallocation.projectTaskAllocation.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular app access
public class AuthController {

	@Autowired
	private AuthService authService;
        @PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
		try {
			UserDTO createdUser = authService.register(registerDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		} catch (EmailAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("error", "Email already exists."));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("error", "An error occurred during registration."));
		}
	}
       @PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) throws InvalidCredentialsException {
//        return ResponseEntity.ok(authService.login(loginRequest.getEmail(), loginRequest.getPassword()));
		// --->return ResponseEntity.ok("Success");
		try {
			UserDTO user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
			return ResponseEntity.ok(user); // or return a custom response if needed
		} catch (InvalidCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
		}
	}
}
