package com.taskallocation.projectTaskAllocation.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDTO {
	private Integer taskId;

	@NotBlank(message = "{task.title.notblank}")
	@Size(max = 255, message = "{task.title.size}")
	private String title;

	@Size(max = 1000, message = "{task.description.size}")
	private String description;

	@NotNull(message = "{task.priority.notnull}")
	private TaskPriority priority;

	@NotNull(message = "{task.status.notnull}")
	private TaskStatus status;

	@Min(value = 0, message = "{task.estimatedHours.min}")
	private Integer estimatedHours;

	@Min(value = 0, message = "{task.actualHours.min}")
	private Integer actualHours;

	@NotNull(message = "{task.requiredSkillId.notnull}")
	private Integer requiredSkillId;

	@NotNull(message = "{task.assignedTo.notnull}")
	private Integer assignedTo;

	@NotNull(message = "{task.deadline.notnull}")
	@FutureOrPresent(message = "{task.deadline.futureOrPresent}")
	private LocalDate deadline;

	@NotNull(message = "{task.startDate.notnull}")
	@PastOrPresent(message = "{task.startDate.pastOrPresent}")
	private LocalDate startDate;

	@NotNull(message = "{task.endDate.notnull}")
	@FutureOrPresent(message = "{task.endDate.futureOrPresent}")
	private LocalDate endDate;

	@NotNull(message = "{task.createdBy.notnull}")
	private Integer createdBy;
	private SkillDTO skill;
	

	private List<String> skills;


	public Integer getTaskId() {
		return taskId;
	}


	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public TaskPriority getPriority() {
		return priority;
	}


	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}


	public TaskStatus getStatus() {
		return status;
	}


	public void setStatus(TaskStatus status) {
		this.status = status;
	}


	public Integer getEstimatedHours() {
		return estimatedHours;
	}


	public void setEstimatedHours(Integer estimatedHours) {
		this.estimatedHours = estimatedHours;
	}


	public Integer getActualHours() {
		return actualHours;
	}


	public void setActualHours(Integer actualHours) {
		this.actualHours = actualHours;
	}


	public Integer getRequiredSkillId() {
		return requiredSkillId;
	}


	public void setRequiredSkillId(Integer requiredSkillId) {
		this.requiredSkillId = requiredSkillId;
	}


	public Integer getAssignedTo() {
		return assignedTo;
	}


	public void setAssignedTo(Integer assignedTo) {
		this.assignedTo = assignedTo;
	}


	public LocalDate getDeadline() {
		return deadline;
	}


	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public Integer getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}


	public SkillDTO getSkill() {
		return skill;
	}


	public void setSkill(SkillDTO skill) {
		this.skill = skill;
	}


	public List<String> getSkills() {
		return skills;
	}


	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	
}
