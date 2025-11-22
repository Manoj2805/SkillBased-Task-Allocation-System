package com.taskallocation.projectTaskAllocation.service;

import java.util.List;

import com.taskallocation.projectTaskAllocation.dto.TaskUpdateDTO;
import com.taskallocation.projectTaskAllocation.entity.TaskUpdate;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;

public interface TaskUpdateService {
    TaskUpdateDTO updateTaskStatus(TaskUpdateDTO dto) throws UserNotFoundException;
    List<TaskUpdate> getUpdatesByTask(Integer taskId) throws UserNotFoundException;
}