package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.UserSkillDTO;
import com.taskallocation.projectTaskAllocation.entity.UserSkill;

public class UserSkillMapper {

    public static UserSkillDTO toDTO(UserSkill entity) {
        if (entity == null) return null;
        UserSkillDTO dto = new UserSkillDTO(null, null, null, null, null);
//        dto.setUserId(entity.getUserId());
//        dto.setSkillId(entity.getSkillId());
        //dto.setProficiencyLevel(entity.getProficiencyLevel());
        dto.setLastUsed(entity.getLastUsed());
        return dto;
    }

    public static UserSkill toEntity(UserSkillDTO dto) {
        if (dto == null) return null;
        UserSkill entity = new UserSkill();
//        entity.setUserId(dto.getUserId());
//        entity.setSkillId(dto.getSkillId());
        //entity.setProficiencyLevel(dto.getProficiencyLevel());
        entity.setLastUsed(dto.getLastUsed());
        return entity;
    }
}
