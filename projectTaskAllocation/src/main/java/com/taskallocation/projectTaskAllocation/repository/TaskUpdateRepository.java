package com.taskallocation.projectTaskAllocation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.entity.TaskUpdate;

@Repository
public interface TaskUpdateRepository extends CrudRepository<TaskUpdate, Integer> {
	List<TaskUpdate> findByUpdateId(Integer updateId);         // Correct nested property
//	List<TaskUpdate> findByUpdatedByUserId(Integer updatedById); // Match the field name\

	List<TaskUpdate> findByTaskTaskId(Integer taskId);
	Optional<TaskUpdate> findTopByTask_TaskIdAndUpdatedBy_UserIdOrderByUpdatedAtDesc(Integer taskId, Integer updatedBy);
	void deleteByTask_TaskId(Integer taskId);
}

