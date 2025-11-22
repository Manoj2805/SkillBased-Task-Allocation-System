package com.taskallocation.projectTaskAllocation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskallocation.projectTaskAllocation.entity.Notification;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.NotificationRepository;
import com.taskallocation.projectTaskAllocation.repository.UserRepository;

@Service
public class NotificationServiceImpl {
	
    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private UserRepository userRepository;
    public void notifyUser(Notification notification) throws UserNotFoundException {
        notificationRepository.save(notification);
    }


    public List<Notification> getUnreadNotifications(Integer userId) throws UserNotFoundException {
        // Check if user exists
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        // Fetch unread notifications
        List<Notification> notifications = notificationRepository.findByIsReadFalseAndUserUserId(userId);
        // Optional: also throw if there are no unread notifications
        if (notifications == null || notifications.isEmpty()) {
            throw new UserNotFoundException("No unread notifications for user with ID: " + userId);
        }
        return notifications;
    }

}