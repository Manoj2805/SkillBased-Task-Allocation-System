package com.taskallocation.projectTaskAllocation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.dto.TaskUpdateDTO;
import com.taskallocation.projectTaskAllocation.entity.TaskUpdate;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.TaskUpdateServiceImpl;

@RestController
@RequestMapping("/api/task-updates")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskUpdateController {

	@Autowired
	private TaskUpdateServiceImpl taskUpdateService;

    @PutMapping
	public ResponseEntity<TaskUpdateDTO> updateTaskStatus(@RequestBody TaskUpdateDTO dto) throws UserNotFoundException {
		TaskUpdateDTO updated = taskUpdateService.updateTaskStatus(dto);
		return ResponseEntity.ok(updated);
	}

	@GetMapping("/task/{taskId}")
	public List<TaskUpdate> getUpdatesByTask(@PathVariable Integer taskId) throws UserNotFoundException {
		return taskUpdateService.getUpdatesByTask(taskId);
	}
}