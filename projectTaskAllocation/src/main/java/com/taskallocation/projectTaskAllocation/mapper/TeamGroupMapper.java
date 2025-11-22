package com.taskallocation.projectTaskAllocation.mapper;

import com.taskallocation.projectTaskAllocation.dto.TeamGroupDTO;
import com.taskallocation.projectTaskAllocation.entity.TeamGroup;

public class TeamGroupMapper {

    public static TeamGroupDTO toDTO(TeamGroup entity) {
        if (entity == null) return null;
        TeamGroupDTO dto = new TeamGroupDTO();
        dto.setTeamId(entity.getTeamId());
        dto.setTeamName(entity.getTeamName());
        dto.setProjectName(entity.getProjectName());
//        dto.setCreatedBy(entity.getCreatedBy());
//        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static TeamGroup toEntity(TeamGroupDTO dto) {
        if (dto == null) return null;
        TeamGroup entity = new TeamGroup();
        entity.setTeamId(dto.getTeamId());
        entity.setTeamName(dto.getTeamName());
        entity.setProjectName(dto.getProjectName());
//        entity.setCreatedBy(dto.getCreatedBy());
//        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
}