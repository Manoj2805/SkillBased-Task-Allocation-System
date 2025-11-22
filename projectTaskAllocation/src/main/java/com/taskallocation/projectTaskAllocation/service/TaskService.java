package com.taskallocation.projectTaskAllocation.service;

import java.time.LocalDate;
import java.util.List;

import com.taskallocation.projectTaskAllocation.dto.TaskDTO;
import com.taskallocation.projectTaskAllocation.dto.TaskStatus;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;

public interface TaskService {
    TaskDTO createTask(TaskDTO taskDTO) throws UserNotFoundException;
    TaskDTO updateTask(TaskDTO taskDTO) throws UserNotFoundException;
    void deleteTask(Integer taskId) throws UserNotFoundException;
    TaskDTO getTaskById(Integer taskId) throws UserNotFoundException;
    List<TaskDTO> getAllTasks() throws UserNotFoundException;
    List<TaskDTO> getTasksByCreatedBy(Integer createdById) throws UserNotFoundException;
    List<TaskDTO> getTasksByAssignedTo(Integer assignedToId) throws UserNotFoundException;
    List<TaskDTO> getTasksByStatus(TaskStatus status) throws UserNotFoundException;
    List<TaskDTO> getTasksBySkill(Integer skillId) throws UserNotFoundException;
    List<TaskDTO> getTasksByDeadlineRange(LocalDate start, LocalDate end) throws UserNotFoundException;
}
