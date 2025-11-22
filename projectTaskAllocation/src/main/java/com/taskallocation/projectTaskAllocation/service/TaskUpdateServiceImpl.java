package com.taskallocation.projectTaskAllocation.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskallocation.projectTaskAllocation.dto.TaskStatus;
import com.taskallocation.projectTaskAllocation.dto.TaskUpdateDTO;
import com.taskallocation.projectTaskAllocation.entity.Task;
import com.taskallocation.projectTaskAllocation.entity.TaskUpdate;
import com.taskallocation.projectTaskAllocation.entity.User;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.TaskRepository;
import com.taskallocation.projectTaskAllocation.repository.TaskUpdateRepository;
import com.taskallocation.projectTaskAllocation.repository.UserRepository;

@Service
public class TaskUpdateServiceImpl implements TaskUpdateService {

    @Autowired
    private TaskUpdateRepository taskUpdateRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;
       
       @Override
    public TaskUpdateDTO updateTaskStatus(TaskUpdateDTO dto) throws UserNotFoundException {
        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new UserNotFoundException("Task not found"));
        User user = userRepository.findById(dto.getUpdatedBy())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Convert and validate status
        TaskStatus status = TaskStatus.valueOf(dto.getStatus().trim().toUpperCase());

        task.setStatus(status);
        taskRepository.save(task);
         Optional<TaskUpdate> optionalUpdate = taskUpdateRepository
                .findTopByTask_TaskIdAndUpdatedBy_UserIdOrderByUpdatedAtDesc(dto.getTaskId(), dto.getUpdatedBy());

        TaskUpdate update = optionalUpdate.orElseGet(() -> {
            TaskUpdate newUpdate = new TaskUpdate();
            newUpdate.setTask(task);
            newUpdate.setUpdatedBy(user);
            return newUpdate;
        });
          update.setComment(dto.getComment());
        update.setStatus(status); // use enum
        update.setUpdatedAt(LocalDateTime.now());

        TaskUpdate saved = taskUpdateRepository.save(update);

        TaskUpdateDTO responseDTO = new TaskUpdateDTO();
        responseDTO.setUpdateId(saved.getUpdateId());
        responseDTO.setTaskId(saved.getTask().getTaskId());
        responseDTO.setComment(saved.getComment());
        responseDTO.setStatus(saved.getStatus().name()); // return string to frontend
        responseDTO.setUpdatedBy(saved.getUpdatedBy().getUserId());

        return responseDTO;
    }
    
    @Override
    public List<TaskUpdate> getUpdatesByTask(Integer taskId) throws UserNotFoundException {
        if (!taskRepository.existsById(taskId)) {
            throw new UserNotFoundException("Task not found with ID: " + taskId);
        }
        return taskUpdateRepository.findByTaskTaskId(taskId);
    }
}
