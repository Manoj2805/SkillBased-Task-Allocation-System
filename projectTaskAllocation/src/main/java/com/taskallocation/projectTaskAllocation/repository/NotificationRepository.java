package com.taskallocation.projectTaskAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.entity.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    //List<Notification> findByUserId(Integer userId);
   // List<Notification> findByIsReadFalseAndUserId(Integer userId);
    List<Notification> findByUserUserId(Integer userId);
    List<Notification> findByIsReadFalseAndUserUserId(Integer userId);
}
