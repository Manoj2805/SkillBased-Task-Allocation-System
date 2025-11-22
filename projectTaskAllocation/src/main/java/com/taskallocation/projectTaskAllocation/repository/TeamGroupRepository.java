package com.taskallocation.projectTaskAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.entity.TeamGroup;

@Repository
public interface TeamGroupRepository extends CrudRepository<TeamGroup, Integer> {
    TeamGroup findByTeamName(String teamName);
    List<TeamGroup> findByCreatedByUserId(Integer userId);  // Corrected method
}