package com.taskallocation.projectTaskAllocation.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class TeamMemberId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
    private Integer teamId;
    
    public TeamMemberId() {
    }
    
    public TeamMemberId(Integer userId, Integer teamId) {
        this.userId = userId;
        this.teamId = teamId;
    }

	// Getters, Setters, equals(), and hashCode() methods
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
     
//    public Integer getTaskId() {
//		return taskId;
//	}
//
//	public void setTaskId(Integer taskId) {
//		this.taskId = taskId;
//	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMemberId that = (TeamMemberId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, teamId);
    }
}
