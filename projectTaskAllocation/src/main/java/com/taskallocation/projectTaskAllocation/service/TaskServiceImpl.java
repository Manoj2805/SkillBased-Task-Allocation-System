package com.taskallocation.projectTaskAllocation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskallocation.projectTaskAllocation.dto.SkillDTO;
import com.taskallocation.projectTaskAllocation.dto.TaskDTO;
import com.taskallocation.projectTaskAllocation.dto.TaskStatus;
import com.taskallocation.projectTaskAllocation.entity.Skill;
import com.taskallocation.projectTaskAllocation.entity.Task;
import com.taskallocation.projectTaskAllocation.entity.TaskUpdate;
import com.taskallocation.projectTaskAllocation.entity.User;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.SkillRepository;
import com.taskallocation.projectTaskAllocation.repository.TaskRepository;
import com.taskallocation.projectTaskAllocation.repository.TaskUpdateRepository;
import com.taskallocation.projectTaskAllocation.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final TaskUpdateRepository taskUpdateRepository;
    
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, SkillRepository skillRepository,TaskUpdateRepository taskUpdateRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.taskUpdateRepository=taskUpdateRepository;
    }
     @Override
    public TaskDTO createTask(TaskDTO dto) throws UserNotFoundException {
        Task task = mapToEntity(dto);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        Task saved = taskRepository.save(task);

        TaskUpdate update = new TaskUpdate();
        update.setTask(saved);
        update.setComment("Task created");
        update.setStatus(saved.getStatus()); // enum
        update.setUpdatedBy(saved.getAssignedTo()); // ✅ Directly use User object
        update.setUpdatedAt(LocalDateTime.now());

        taskUpdateRepository.save(update);

        return mapToDTO(saved);
    }
    @Override
    public TaskDTO updateTask(TaskDTO dto) throws UserNotFoundException{
        Optional<Task> existing = taskRepository.findById(dto.getTaskId());
        if (existing.isPresent()) {
            Task task = existing.get();
            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setPriority(dto.getPriority());
            task.setStatus(dto.getStatus());
            task.setEstimatedHours(dto.getEstimatedHours());
            task.setActualHours(dto.getActualHours());
            task.setStartDate(dto.getStartDate());
            task.setEndDate(dto.getEndDate());
            task.setDeadline(dto.getDeadline());
            task.setUpdatedAt(LocalDateTime.now());

            if (dto.getRequiredSkillId() != null) {
                Skill skill = skillRepository.findById(dto.getRequiredSkillId())
                    .orElseThrow(() -> new  UserNotFoundException("Skill not found"));
                task.setRequiredSkill(skill);
            }

            if (dto.getAssignedTo() != null) {
                User user = userRepository.findById(dto.getAssignedTo())
                    .orElseThrow(() -> new  UserNotFoundException("User not found"));
                task.setAssignedTo(user);
            }

            return mapToDTO(taskRepository.save(task));
        } 
        else {
            throw new  UserNotFoundException("Task not found with ID: " + dto.getTaskId());
        }
    }
@Override
    @Transactional
    public void deleteTask(Integer taskId) throws UserNotFoundException{
    	taskUpdateRepository.deleteByTask_TaskId(taskId);
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDTO getTaskById(Integer taskId) throws UserNotFoundException{
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new  UserNotFoundException("Task not found"));
        return mapToDTO(task);
    }

    @Override
    public List<TaskDTO> getAllTasks() throws UserNotFoundException{
        return ((List<Task>) taskRepository.findAll())
            .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByCreatedBy(Integer createdById) throws UserNotFoundException{
        return taskRepository.findByCreatedByUserId(createdById)
            .stream().map(this::mapToDTO).collect(Collectors.toList());
    }
 @Override
    public List<TaskDTO> getTasksByAssignedTo(Integer assignedToId) throws UserNotFoundException{
        return taskRepository.findByAssignedToUserId(assignedToId)
            .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByStatus(TaskStatus status) throws UserNotFoundException{
        return taskRepository.findByStatus(status)
            .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksBySkill(Integer skillId) throws UserNotFoundException{
        return taskRepository.findByRequiredSkillSkillId(skillId)
            .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByDeadlineRange(LocalDate start, LocalDate end) throws UserNotFoundException{
        return taskRepository.findByDeadlineBetween(start, end)
            .stream().map(this::mapToDTO).collect(Collectors.toList());
    }
private TaskDTO mapToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setTaskId(task.getTaskId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setPriority(task.getPriority());
        dto.setStatus(task.getStatus());
        dto.setEstimatedHours(task.getEstimatedHours());
        dto.setActualHours(task.getActualHours());
        dto.setDeadline(task.getDeadline());
        dto.setStartDate(task.getStartDate());
        dto.setEndDate(task.getEndDate());
        dto.setCreatedBy(task.getCreatedBy().getUserId());
        List<String> skillNames = task.getTaskSkills() != null
                ? task.getTaskSkills().stream()
                      .map(ts -> ts.getSkill().getSkillName())
                      .collect(Collectors.toList())
                : new ArrayList<>();

            dto.setSkills(skillNames);
            
            
        dto.setAssignedTo(task.getAssignedTo() != null ? task.getAssignedTo().getUserId() : null);
        if (task.getRequiredSkill() != null) {
            Skill skill = task.getRequiredSkill();
            dto.setRequiredSkillId(skill.getSkillId());
             SkillDTO skillDTO = new SkillDTO(null, null, null, null, null, null);
            skillDTO.setSkillId(skill.getSkillId());
            skillDTO.setSkillName(skill.getSkillName());
            skillDTO.setSkillDescription(skill.getSkillDescription());
            skillDTO.setCategory(skill.getCategory());
            skillDTO.setLevel(skill.getLevel());
            skillDTO.setCreatedAt(skill.getCreatedAt());// or other fields
            dto.setSkill(skillDTO);
        }
         return dto;
}
private Task mapToEntity(TaskDTO dto) throws UserNotFoundException{
        Task task = new Task();
        task.setTaskId(dto.getTaskId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
        task.setEstimatedHours(dto.getEstimatedHours());
        task.setActualHours(dto.getActualHours());
        task.setDeadline(dto.getDeadline());
        task.setStartDate(dto.getStartDate());
        task.setEndDate(dto.getEndDate());

        if (dto.getAssignedTo() != null) {
            User user = userRepository.findById(dto.getAssignedTo())
                .orElseThrow(() -> new UserNotFoundException("Assigned user not found"));
            task.setAssignedTo(user);
        }

        if (dto.getRequiredSkillId() != null) {
            Skill skill = skillRepository.findById(dto.getRequiredSkillId())
                .orElseThrow(() -> new  UserNotFoundException("Required skill not found"));
            task.setRequiredSkill(skill);
        }
        //
        User creator = userRepository.findById(dto.getCreatedBy())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + dto.getCreatedBy()));
            task.setCreatedBy(creator);
            
        
      return task;
    }
}