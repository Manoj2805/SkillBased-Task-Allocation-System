package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.TaskDTO;
import com.taskallocation.projectTaskAllocation.entity.Task;

public class TaskMapper {

    public static TaskDTO toDTO(Task entity) {
        if (entity == null) return null;
        TaskDTO dto = new TaskDTO();
        dto.setTaskId(entity.getTaskId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPriority(entity.getPriority());
        dto.setStatus(entity.getStatus());
        dto.setEstimatedHours(entity.getEstimatedHours());
        dto.setActualHours(entity.getActualHours());
        dto.setRequiredSkillId(entity.getRequiredSkill().getSkillId());
       dto.setAssignedTo(entity.getAssignedTo().getUserId());
        dto.setDeadline(entity.getDeadline());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
       //dto.setCreatedBy(entity.getCreatedBy().getCreatedAt());
        return dto;
    }
    
    public static Task toEntity(TaskDTO dto) {
        if (dto == null) return null;
        Task entity = new Task();
        entity.setTaskId(dto.getTaskId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPriority(dto.getPriority());
        entity.setStatus(dto.getStatus());
        entity.setEstimatedHours(dto.getEstimatedHours());
        entity.setActualHours(dto.getActualHours());     
        //entity.setRequiredSkill(dto.getSkill());
        //entity.setAssignedTo(dto.getAssignedTo().get);
        entity.setDeadline(dto.getDeadline());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
       // entity.setCreatedBy(dto.getCreatedBy());
        return entity;
    }
}
