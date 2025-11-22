package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.TaskUpdateDTO;
import com.taskallocation.projectTaskAllocation.entity.TaskUpdate;

public class TaskUpdateMapper {

    public static TaskUpdateDTO toDTO(TaskUpdate entity) {
        if (entity == null) return null;
        TaskUpdateDTO dto = new TaskUpdateDTO();
        dto.setUpdateId(entity.getUpdateId());
//        dto.setTaskId(entity.getTaskId());
//        dto.setComment(entity.getComment());
//        dto.setStatus(entity.getStatus());
//        dto.setUpdatedBy(entity.getUpdatedBy());
//        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public static TaskUpdate toEntity(TaskUpdateDTO dto) {
        if (dto == null) return null;
        TaskUpdate entity = new TaskUpdate();
//        entity.setUpdateId(dto.getUpdateId());
//        entity.setTaskId(dto.getTaskId());
//        entity.setComment(dto.getComment());
//        entity.setStatus(dto.getStatus());
//        entity.setUpdatedBy(dto.getUpdatedBy());
//        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }
}
