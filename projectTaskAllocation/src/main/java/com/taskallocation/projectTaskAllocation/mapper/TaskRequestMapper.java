package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.TaskRequestDTO;
import com.taskallocation.projectTaskAllocation.entity.TaskRequest;

public class TaskRequestMapper {

    public static TaskRequestDTO toDTO(TaskRequest entity) {
        if (entity == null) return null;
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setRequestId(entity.getRequestId());
//        dto.setUserId(entity.getUserId());
//        dto.setTaskId(entity.getTaskId());
        dto.setRequestMessage(entity.getRequestMessage());
        dto.setRequestDate(entity.getRequestDate());
//        dto.setStatus(entity.getStatus());
//        dto.setRespondedBy(entity.getRespondedBy());
        dto.setRespondedAt(entity.getRespondedAt());
        return dto;
    }

    public static TaskRequest toEntity(TaskRequestDTO dto) {
        if (dto == null) return null;
        TaskRequest entity = new TaskRequest();
        entity.setRequestId(dto.getRequestId());
//        entity.setUserId(dto.getUserId());
//        entity.setTaskId(dto.getTaskId());
        entity.setRequestMessage(dto.getRequestMessage());
        entity.setRequestDate(dto.getRequestDate());
//        entity.setStatus(dto.getStatus());
//        entity.setRespondedBy(dto.getRespondedBy());
        entity.setRespondedAt(dto.getRespondedAt());
        return entity;
    }
}
