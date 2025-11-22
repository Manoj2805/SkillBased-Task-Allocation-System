package com.taskallocation.projectTaskAllocation.entity;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Data
@Entity
public class TeamMember {
    
    @EmbeddedId
    private TeamMemberId teamMemberId;  // Embedded composite key
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Task task;
    
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "task_id", insertable = false, updatable = false)
//    private Task task;
//    
    private LocalDate joinedOn;
    private String roleInTeam;
	public void setTaskId(Integer taskId) {
		// TODO Auto-generated method stub
		
	}
    
	public TeamMemberId getTeamMemberId() {
	    return teamMemberId;
	}

	public Integer getUserId() {
	    return getTeamMemberId().getUserId();
	}
    
	public Task getTask() {
	    return task;
	}

	public Integer getTaskId() {
	    return getTask().getTaskId();
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getJoinedOn() {
		return joinedOn;
	}

	public void setJoinedOn(LocalDate joinedOn) {
		this.joinedOn = joinedOn;
	}

	public String getRoleInTeam() {
		return roleInTeam;
	}

	public void setRoleInTeam(String roleInTeam) {
		this.roleInTeam = roleInTeam;
	}

	public void setTeamMemberId(TeamMemberId teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	

}