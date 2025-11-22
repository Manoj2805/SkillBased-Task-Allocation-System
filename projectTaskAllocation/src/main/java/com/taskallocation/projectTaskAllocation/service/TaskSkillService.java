package com.taskallocation.projectTaskAllocation.service;

import java.util.List;

import com.taskallocation.projectTaskAllocation.dto.TaskSkillDTO;
import com.taskallocation.projectTaskAllocation.entity.TaskSkill;

public interface TaskSkillService {
    TaskSkill assignSkillToTask(TaskSkillDTO dto);
    List<TaskSkill> getSkillsByTaskId(Integer taskId);
}