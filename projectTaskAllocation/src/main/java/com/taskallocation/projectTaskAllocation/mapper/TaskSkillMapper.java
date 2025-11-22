package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.TaskSkillDTO;
import com.taskallocation.projectTaskAllocation.entity.TaskSkill;

public class TaskSkillMapper {

    public static TaskSkillDTO toDTO(TaskSkill entity) {
        if (entity == null) return null;
        TaskSkillDTO dto = new TaskSkillDTO();
//        dto.setTaskId(entity.getTaskId());
//        dto.setSkillId(entity.getSkillId());
        //dto.setImportanceLevel(entity.getImportanceLevel());
        return dto;
    }

    public static TaskSkill toEntity(TaskSkillDTO dto) {
        if (dto == null) return null;
        TaskSkill entity = new TaskSkill();
//        entity.setTaskId(dto.getTaskId());
//        entity.setSkillId(dto.getSkillId());
        //entity.setImportanceLevel(dto.getImportanceLevel());
        return entity;
    }
}
