package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.NotificationDTO;
import com.taskallocation.projectTaskAllocation.entity.Notification;

public class NotificationMapper {

    public static NotificationDTO toDTO(Notification entity) {
        if (entity == null) return null;
        NotificationDTO dto = new NotificationDTO();
        dto.setNotificationId(entity.getNotificationId());
        //dto.setUserId(entity.getUserId());
        dto.setMessage(entity.getMessage());
        //dto.setType(entity.getType());
        dto.setIsRead(entity.getIsRead());
        //dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static Notification toEntity(NotificationDTO dto) {
        if (dto == null) return null;
        Notification entity = new Notification();
        entity.setNotificationId(dto.getNotificationId());
        //entity.setUserId(dto.getUserId());
        entity.setMessage(dto.getMessage());
        //entity.setType(dto.getType());
        entity.setIsRead(dto.getIsRead());
        //entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
}
