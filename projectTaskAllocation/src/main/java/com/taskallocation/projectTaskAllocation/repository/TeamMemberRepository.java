package com.taskallocation.projectTaskAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.entity.TeamMember;
import com.taskallocation.projectTaskAllocation.entity.TeamMemberId;

@Repository
public interface TeamMemberRepository extends CrudRepository<TeamMember, TeamMemberId> {
	List<TeamMember> findByTeamMemberId_TeamId(Integer teamId);

	List<TeamMember> findByTeamMemberId_UserId(Integer userId);

	TeamMember findByTeamMemberId_UserIdAndTeamMemberId_TeamId(Integer userId, Integer teamId);
	// Optional<TeamMember> findById(Integer teamId);
}