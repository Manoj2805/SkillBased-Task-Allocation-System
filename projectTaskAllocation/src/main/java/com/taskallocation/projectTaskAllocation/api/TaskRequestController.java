package com.taskallocation.projectTaskAllocation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.taskallocation.projectTaskAllocation.dto.RequestStatus;
import com.taskallocation.projectTaskAllocation.dto.TaskRequestDTO;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.TaskRequestService;

@RestController
@RequestMapping("/api/task-requests")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskRequestController {

	@Autowired
	private TaskRequestService taskRequestService;
         @PostMapping
	public ResponseEntity<TaskRequestDTO> createRequest(@RequestBody TaskRequestDTO dto) throws UserNotFoundException {
		return new ResponseEntity<>(taskRequestService.createTaskRequest(dto), HttpStatus.CREATED);
		// responed_by (is getting the null set that as the values of
		// 1,2)-->Manager,Admin
		// Completed
	}

	@GetMapping("/user/{userId}")
	public List<TaskRequestDTO> getRequestsByUser(@PathVariable Integer userId) throws UserNotFoundException {
		return taskRequestService.getRequestsByUser(userId);// completed
	}

	@GetMapping("/task/{taskId}")
	public List<TaskRequestDTO> getRequestsByTask(@PathVariable Integer taskId) throws UserNotFoundException {
		return taskRequestService.getRequestsByTask(taskId);// completed
	}
@GetMapping("/all-request-ids")
	public ResponseEntity<List<Integer>> getAllRequestIds() {
		List<Integer> requestIds = taskRequestService.getAllRequestIds();
		return ResponseEntity.ok(requestIds);
	}
@GetMapping("/requests")
	public ResponseEntity<List<TaskRequestDTO>> getAllRequests() {
		try {
			List<TaskRequestDTO> requests = taskRequestService.getAllRequests();
			return ResponseEntity.ok(requests);
		} catch (Exception e) {
			e.printStackTrace(); // Log the actual error to the console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
@PutMapping("/{requestId}/respond")
	public ResponseEntity<TaskRequestDTO> respondToRequest(@PathVariable Integer requestId,
			@RequestParam RequestStatus status, @RequestParam Integer respondedBy) throws UserNotFoundException {
		return ResponseEntity.ok(taskRequestService.respondToRequest(requestId, status, respondedBy));

		// http://localhost:8080/api/task-requests/4/respond?status=Approved&respondedBy=2
		// http://localhost:8080/api/task-requests/4/respond?status=APPROVED&respondedBy=2
	}

	@DeleteMapping("/{requestId}")
	public ResponseEntity<Void> deleteRequest(@PathVariable Integer requestId) throws UserNotFoundException {
		taskRequestService.deleteRequest(requestId);
		return ResponseEntity.noContent().build();
		// completed
	}
}