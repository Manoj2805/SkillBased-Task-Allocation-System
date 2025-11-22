package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.SkillDTO;
import com.taskallocation.projectTaskAllocation.entity.Skill;

public class SkillMapper {

    public static SkillDTO toDTO(Skill entity) {
        if (entity == null) return null;
        SkillDTO dto = new SkillDTO(null, null, null, null, null, null);
        dto.setSkillId(entity.getSkillId());
        dto.setSkillName(entity.getSkillName());
        dto.setSkillDescription(entity.getSkillDescription());
        dto.setCategory(entity.getCategory());
       // dto.setLevel(entity.getLevel().);
        return dto;
    }

    public static Skill toEntity(SkillDTO dto) {
        if (dto == null) return null;
        Skill entity = new Skill();
        entity.setSkillId(dto.getSkillId());
        entity.setSkillName(dto.getSkillName());
        entity.setSkillDescription(dto.getSkillDescription());
        entity.setCategory(dto.getCategory());
       // entity.setLevel(dto.getLevel());
        return entity;
    }
}
