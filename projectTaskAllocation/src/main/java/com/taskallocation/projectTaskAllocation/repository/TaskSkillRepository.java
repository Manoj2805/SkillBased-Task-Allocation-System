package com.taskallocation.projectTaskAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.entity.TaskSkill;
import com.taskallocation.projectTaskAllocation.entity.TaskSkillId;

@Repository
public interface TaskSkillRepository extends CrudRepository<TaskSkill, TaskSkillId> {
	 List<TaskSkill> findByIdTaskId(Integer taskId); // references id.taskId
	 TaskSkill findByIdTaskIdAndIdSkillId(Integer taskId, Integer skillId); // references id.taskId and id.skillId
}
