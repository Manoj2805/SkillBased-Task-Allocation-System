package com.taskallocation.projectTaskAllocation.entity;

import java.time.LocalDateTime;

import com.taskallocation.projectTaskAllocation.dto.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "taskupdate")
public class TaskUpdate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer updateId;

	@ManyToOne
	// @JsonIgnore
	@JoinColumn(name = "task_id", nullable = false)
	private Task task;

	private String comment;

	@Enumerated(EnumType.STRING)
	private TaskStatus status;

	@ManyToOne
	// @JsonIgnore
	@JoinColumn(name = "updated_by", nullable = false)
	private User updatedBy;

	private LocalDateTime updatedAt;

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	

}
