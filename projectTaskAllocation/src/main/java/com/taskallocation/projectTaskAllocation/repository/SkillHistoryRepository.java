package com.taskallocation.projectTaskAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.entity.SkillHistory;

@Repository
public interface SkillHistoryRepository extends CrudRepository<SkillHistory, Integer> {
    List<SkillHistory> findByUserUserId(Integer userId); 
    SkillHistory findByUserUserIdAndSkillSkillId(Integer userId, Integer skillId);
}
