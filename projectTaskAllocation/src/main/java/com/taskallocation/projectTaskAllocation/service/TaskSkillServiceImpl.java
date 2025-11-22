package com.taskallocation.projectTaskAllocation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskallocation.projectTaskAllocation.dto.TaskSkillDTO;
import com.taskallocation.projectTaskAllocation.entity.TaskSkill;
import com.taskallocation.projectTaskAllocation.entity.TaskSkillId;
import com.taskallocation.projectTaskAllocation.repository.TaskSkillRepository;

@Service
public class TaskSkillServiceImpl implements TaskSkillService {
	
    @Autowired
    private TaskSkillRepository taskSkillRepository;
    
    public TaskSkill assignSkillToTask(TaskSkillDTO dto) {
        TaskSkillId id = new TaskSkillId(dto.getTaskId(), dto.getSkillId());

        TaskSkill taskSkill = new TaskSkill();
        taskSkill.setId(id);
        taskSkill.setImportanceLevel(dto.getImportanceLevel()); // Directly set the enum

        return taskSkillRepository.save(taskSkill);
    }
    
    public List<TaskSkill> getSkillsByTaskId(Integer taskId) {
    	return taskSkillRepository.findByIdTaskId(taskId);
    }
    
}
