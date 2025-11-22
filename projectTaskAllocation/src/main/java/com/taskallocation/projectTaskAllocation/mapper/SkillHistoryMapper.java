package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.SkillHistoryDTO;
import com.taskallocation.projectTaskAllocation.entity.SkillHistory;

public class SkillHistoryMapper {

    public static SkillHistoryDTO toDTO(SkillHistory entity) {
        if (entity == null) return null;
        SkillHistoryDTO dto = new SkillHistoryDTO();
        dto.setHistoryId(entity.getHistoryId());
//        dto.setUserId(entity.getUserId());
//        dto.setSkillId(entity.getSkillId());
        dto.setAcquiredOn(entity.getAcquiredOn());
        dto.setRemarks(entity.getRemarks());
        return dto;
    }

    public static SkillHistory toEntity(SkillHistoryDTO dto) {
        if (dto == null) return null;
        SkillHistory entity = new SkillHistory();
        entity.setHistoryId(dto.getHistoryId());
//        entity.setUserId(dto.getUserId());
//        entity.setSkillId(dto.getSkillId());
        entity.setAcquiredOn(dto.getAcquiredOn());
        entity.setRemarks(dto.getRemarks());
        return entity;
    }
}