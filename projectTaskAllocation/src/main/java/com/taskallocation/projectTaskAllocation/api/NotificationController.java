package com.taskallocation.projectTaskAllocation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.entity.Notification;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.NotificationServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Autowired
	private NotificationServiceImpl notificationService;

	@PostMapping
	public ResponseEntity<String> notifyUser(@RequestBody @Valid Notification notification) throws Exception {
		notificationService.notifyUser(notification);
		return ResponseEntity.status(HttpStatus.CREATED).body("Notification created successfully");
	}

	@GetMapping("/user/{userId}")
	public List<Notification> getUnreadNotifications(@PathVariable Integer userId) throws UserNotFoundException {
		return notificationService.getUnreadNotifications(userId);
	}
}