package com.taskallocation.projectTaskAllocation.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class TeamMemberDTO {
//	@NotNull(message = "{teamMember.userId.notnull}")
//    private Integer userId;
//
//    @NotNull(message = "{teamMember.teamId.notnull}")
//    private Integer teamId;
//
//    @NotNull(message = "{teamMember.skillId.notnull}")
//    private Integer skillId;
//
//    @NotNull(message = "{teamMember.taskId.notnull}")
//    private Integer taskId;
//
//    @NotNull(message = "{teamMember.joinedOn.notnull}")
//    private LocalDate joinedOn;
//
//    @NotBlank(message = "{teamMember.roleInTeam.notblank}")
//    @Size(max = 100, message = "{teamMember.roleInTeam.size}")
//    private String roleInTeam;

	private Integer userId;
	private Integer teamId;
	private Integer skillId;
	private Integer taskId;
	private LocalDate joinedOn;

	private String name;
	private String email;
	private List<String> skills;
	private List<String> tasks;
	private String workload;
	private String roleInTeam;

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

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<String> getTasks() {
		return tasks;
	}

	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}

	public String getWorkload() {
		return workload;
	}

	public void setWorkload(String workload) {
		this.workload = workload;
	}
	
	
}
