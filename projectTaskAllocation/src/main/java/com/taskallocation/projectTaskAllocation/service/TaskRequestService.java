package com.taskallocation.projectTaskAllocation.service;

import java.util.List;

import com.taskallocation.projectTaskAllocation.dto.RequestStatus;
import com.taskallocation.projectTaskAllocation.dto.TaskRequestDTO;
import com.taskallocation.projectTaskAllocation.dto.TaskRequestWithSkillDTO;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;

public interface TaskRequestService {
	TaskRequestDTO createTaskRequest(TaskRequestDTO dto) throws UserNotFoundException;

	List<TaskRequestDTO> getRequestsByUser(Integer userId) throws UserNotFoundException;

	List<TaskRequestDTO> getRequestsByTask(Integer taskId) throws UserNotFoundException;

	TaskRequestDTO respondToRequest(Integer requestId, RequestStatus status, Integer respondedBy)
			throws UserNotFoundException;

	void deleteRequest(Integer requestId) throws UserNotFoundException;

	List<Integer> getAllRequestIds();

	List<TaskRequestWithSkillDTO> getAllRequestsWithSkillInfo();
    
	    List<TaskRequestDTO> getAllRequests();

	
}
