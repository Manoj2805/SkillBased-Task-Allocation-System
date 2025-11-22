package com.taskallocation.projectTaskAllocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.entity.TaskRequest;

@Repository
public interface TaskRequestRepository extends CrudRepository<TaskRequest, Integer> {
	TaskRequest findByUserUserIdAndTaskTaskId(Integer userId, Integer taskId);
	List<TaskRequest> findByTaskTaskId(Integer taskId);
	List<TaskRequest> findByUserUserId(Integer userId);
	
//	@Query("SELECT tr.requestId FROM TaskRequest tr")
//	List<Integer> findAllRequestIds();
    
//	@Query("SELECT new com.taskallocation.projectTaskAllocation.dto.TaskRequestWithSkillDTO(" +
//		       "tr.requestId, tr.user.id, tr.task.id, tr.requestMessage, tr.requestDate, tr.status, " +
//		       "tr.respondedBy.id, tr.respondedAt, tr.task.requiredSkill.id, tr.task.requiredSkill.name) " +
//		       "FROM TaskRequest tr")
//		List<TaskRequestWithSkillDTO> findAllWithSkillInfo();
//	List<TaskRequestWithSkillDTO> getAllRequestsWithSkillInfo();

	@Query("SELECT r FROM TaskRequest r JOIN FETCH r.user LEFT JOIN FETCH r.task")
	List<TaskRequest> findAllWithUserAndTask();


}

