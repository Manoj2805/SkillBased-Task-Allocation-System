package com.taskallocation.projectTaskAllocation.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.dto.TaskDTO;
import com.taskallocation.projectTaskAllocation.dto.TaskStatus;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

	private final TaskService taskService;

	// "endDate": null(given null in the database),"createdBy": null, "skill":
	// null,actual_hours:null(given null in the database)
	// Completed
	public TaskController(TaskService taskService) throws UserNotFoundException {
		this.taskService = taskService;
	}
@PostMapping
	public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO dto) throws UserNotFoundException {
		return ResponseEntity.ok(taskService.createTask(dto));// completed
		// completed
		// error is at the createdBy is taking only the null then change to that of the
		// Integer of the number Manager(2)
	}
@PutMapping
	public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO dto) throws UserNotFoundException {
		return ResponseEntity.ok(taskService.updateTask(dto));
		// UPDATED Completed.
	}
@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Integer id) throws UserNotFoundException {
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
		// to delete the row status should modify as the "Assigned"
		// completed
	}
@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable Integer id) throws UserNotFoundException {
		return ResponseEntity.ok(taskService.getTaskById(id));// completed
	}

	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAllTasks() throws UserNotFoundException {
		return ResponseEntity.ok(taskService.getAllTasks());// completed
	}

	@GetMapping("/created-by/{userId}")
	public ResponseEntity<List<TaskDTO>> getTasksByCreator(@PathVariable Integer userId) throws UserNotFoundException {
		return ResponseEntity.ok(taskService.getTasksByCreatedBy(userId));// completed
	}
@GetMapping("/assigned-to/{userId}")
	public ResponseEntity<List<TaskDTO>> getTasksByAssignee(@PathVariable Integer userId) throws UserNotFoundException {
		return ResponseEntity.ok(taskService.getTasksByAssignedTo(userId));
		// userId is the assignedTo attribute in the database given the manager
		// assigning the task to the Developer -->completed
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<TaskDTO>> getTasksByStatus(@PathVariable TaskStatus status)
			throws UserNotFoundException {
		return ResponseEntity.ok(taskService.getTasksByStatus(status));
		// update the database of the completed,in_progress,BLOCKED
		// http://localhost:8080/api/tasks?status=ASSIGNED
		// completed
	}

	@GetMapping("/skill/{skillId}")
	public ResponseEntity<List<TaskDTO>> getTasksBySkill(@PathVariable Integer skillId) throws UserNotFoundException {
		return ResponseEntity.ok(taskService.getTasksBySkill(skillId));// completed
	}
@GetMapping("/deadline")
	public ResponseEntity<List<TaskDTO>> getTasksByDeadlineRange(@RequestParam("start") String start,
			@RequestParam("end") String end) throws UserNotFoundException {
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);
		return ResponseEntity.ok(taskService.getTasksByDeadlineRange(startDate, endDate));
		// GET http://localhost:8000/api/tasks/deadline?start=2025-05-10&end=2025-05-22
		// completed
	}
}