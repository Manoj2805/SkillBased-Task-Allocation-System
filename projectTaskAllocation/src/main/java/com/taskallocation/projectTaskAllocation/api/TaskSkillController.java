package com.taskallocation.projectTaskAllocation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.dto.TaskSkillDTO;
import com.taskallocation.projectTaskAllocation.entity.TaskSkill;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.TaskSkillServiceImpl;

@RestController
@RequestMapping("/api/task-skills")
public class TaskSkillController {
    @Autowired
    private TaskSkillServiceImpl taskSkillService;
    @PostMapping
    public ResponseEntity<TaskSkill> assignSkillToTask(@RequestBody TaskSkillDTO dto) {
        TaskSkill saved = taskSkillService.assignSkillToTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/task/{taskId}")
    public List<TaskSkill> getSkillsByTaskId(@PathVariable Integer taskId) throws UserNotFoundException{
        return taskSkillService.getSkillsByTaskId(taskId);
    }
    
}