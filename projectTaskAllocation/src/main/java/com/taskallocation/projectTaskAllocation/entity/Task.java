package com.taskallocation.projectTaskAllocation.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.taskallocation.projectTaskAllocation.dto.TaskPriority;
import com.taskallocation.projectTaskAllocation.dto.TaskStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    private Integer estimatedHours;
    private Integer actualHours;

    @ManyToOne
    //@JsonManagedReference(value = "task-skill")
    @JoinColumn(name = "required_skill_id")
    private Skill requiredSkill;

    @ManyToOne
    @JsonManagedReference(value = "task-assigned-to")
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @ManyToOne
    @JsonManagedReference(value = "task-created-by")
    @JoinColumn(name = "created_by")
    private User createdBy;

    private LocalDate deadline;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TaskSkill> taskSkills;
    
    @Override
    public String toString() {
        return "Task{" +
               "id=" + taskId +
               ", name='" + title + '\'' +
               '}';
    }

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

	public Skill getRequiredSkill() {
		return requiredSkill;
	}

	public void setRequiredSkill(Skill requiredSkill) {
		this.requiredSkill = requiredSkill;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<TaskSkill> getTaskSkills() {
		return taskSkills;
	}

	public void setTaskSkills(List<TaskSkill> taskSkills) {
		this.taskSkills = taskSkills;
	}

	
}