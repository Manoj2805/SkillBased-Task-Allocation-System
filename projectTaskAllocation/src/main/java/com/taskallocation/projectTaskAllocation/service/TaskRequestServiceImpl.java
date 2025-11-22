package com.taskallocation.projectTaskAllocation.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskallocation.projectTaskAllocation.dto.RequestStatus;
import com.taskallocation.projectTaskAllocation.dto.TaskRequestDTO;
import com.taskallocation.projectTaskAllocation.dto.TaskRequestWithSkillDTO;
import com.taskallocation.projectTaskAllocation.entity.Task;
import com.taskallocation.projectTaskAllocation.entity.TaskRequest;
import com.taskallocation.projectTaskAllocation.entity.User;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.TaskRepository;
import com.taskallocation.projectTaskAllocation.repository.TaskRequestRepository;
import com.taskallocation.projectTaskAllocation.repository.UserRepository;

@Service
public class TaskRequestServiceImpl implements TaskRequestService {

	@Autowired
	private TaskRequestRepository taskRequestRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;
        
        public TaskRequestDTO createTaskRequest(TaskRequestDTO dto) throws UserNotFoundException {
		// User user = userRepository.findById(dto.getUserId()).orElseThrow();
		User requester = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + dto.getUserId()));

		Task task = taskRepository.findById(dto.getTaskId())
				.orElseThrow(() -> new UserNotFoundException("Request not found"));

		TaskRequest request = new TaskRequest();
                 request.setUser(requester);
		request.setTask(task);
		request.setRequestMessage(dto.getRequestMessage());
		request.setStatus(RequestStatus.REQUESTED);
		request.setRequestDate(LocalDateTime.now());
		// request.setRespondedAt(dto.getRespondedAt());
		// request.setRespondedBy(dto.getRespondedBy());
		if (dto.getRespondedBy() != null) {
			User respondedByUser = userRepository.findById(dto.getRespondedBy()).orElseThrow(
					() -> new UserNotFoundException("Responder not found with ID: " + dto.getRespondedBy()));
			request.setRespondedBy(respondedByUser);
			request.setRespondedAt(dto.getRespondedAt());
		}
                return mapToDTO(taskRequestRepository.save(request));
	}

	public List<TaskRequestDTO> getRequestsByUser(Integer userId) throws UserNotFoundException {
		// Check if user exists
		userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

		// Proceed only if user exists
		return taskRequestRepository.findByUserUserId(userId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
        public List<TaskRequestDTO> getRequestsByTask(Integer taskId) throws UserNotFoundException {
		// Check if task exists
		taskRepository.findById(taskId)
				.orElseThrow(() -> new UserNotFoundException("Task not found with ID: " + taskId));

		// Proceed only if task exists
		return taskRequestRepository.findByTaskTaskId(taskId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
        
         public TaskRequestDTO respondToRequest(Integer requestId, RequestStatus status, Integer respondedBy)
			throws UserNotFoundException {
		TaskRequest request = taskRequestRepository.findById(requestId)
				.orElseThrow(() -> new UserNotFoundException("Request not found"));

		request.setStatus(status);
		request.setRespondedBy(userRepository.findById(respondedBy).orElseThrow());
		request.setRespondedAt(LocalDateTime.now());

		return mapToDTO(taskRequestRepository.save(request));
	}

public void deleteRequest(Integer requestId) throws UserNotFoundException {
		if (!taskRequestRepository.existsById(requestId)) {
			throw new UserNotFoundException("Request not found with ID: " + requestId);
		}
		taskRequestRepository.deleteById(requestId);
	}

	private TaskRequestDTO mapToDTO(TaskRequest entity) {
		TaskRequestDTO dto = new TaskRequestDTO();
		dto.setRequestId(entity.getRequestId());
		dto.setUserId(entity.getUser().getUserId());
		dto.setTaskId(entity.getTask().getTaskId());
		dto.setRequestMessage(entity.getRequestMessage());
		dto.setStatus(entity.getStatus());
		dto.setRequestDate(entity.getRequestDate());
		dto.setRespondedBy(entity.getRespondedBy() != null ? entity.getRespondedBy().getUserId() : null);
		dto.setRespondedAt(entity.getRespondedAt());
		return dto;
	}
        
         @Transactional(readOnly = true)
	public List<TaskRequestDTO> getAllRequests() {
	    List<TaskRequest> requests = taskRequestRepository.findAllWithUserAndTask();

	    return requests.stream().map(req -> {
	        TaskRequestDTO dto = new TaskRequestDTO();
	        dto.setRequestId(req.getRequestId());

	        if (req.getUser() != null) {
	            dto.setUserId(req.getUser().getUserId());
	            dto.setUsername(req.getUser().getName());
	        } else {
	            dto.setUserId(null);
	            dto.setUsername("Unknown");
	        }

	        if (req.getTask() != null) {
	            dto.setTaskId(req.getTask().getTaskId());
	        }

	        dto.setRequestMessage(req.getRequestMessage());
	        dto.setRequestDate(req.getRequestDate());
	        dto.setStatus(req.getStatus());

	        if (req.getRespondedBy() != null) {
	            dto.setRespondedBy(req.getRespondedBy().getUserId());
	        }

	        dto.setRespondedAt(req.getRespondedAt());
	        return dto;
	    }).collect(Collectors.toList());
	}

        @Override
	public List<Integer> getAllRequestIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskRequestWithSkillDTO> getAllRequestsWithSkillInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
