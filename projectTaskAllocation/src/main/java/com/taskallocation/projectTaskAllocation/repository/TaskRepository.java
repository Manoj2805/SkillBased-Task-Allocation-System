package com.taskallocation.projectTaskAllocation.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.dto.TaskStatus;
import com.taskallocation.projectTaskAllocation.entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    Task findByTitle(String title);
    List<Task> findByCreatedByUserId(Integer createdById);
    List<Task> findByAssignedToUserId(Integer assignedToId);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByRequiredSkillSkillId(Integer skillId);
    List<Task> findByDeadlineBetween(LocalDate start, LocalDate end);
    List<Task> findByAssignedTo_UserId(Integer userId);
    //List<Task> findByRequiredSkills_SkillId(Integer skillId);
}