package com.taskallocation.projectTaskAllocation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskUpdateDTO {
	
	@NotNull(message = "{taskUpdate.updateId.notnull}")
    private Integer updateId;

    @NotNull(message = "{taskUpdate.taskId.notnull}")
    private Integer taskId;

    @NotBlank(message = "{taskUpdate.comment.notblank}")
    @Size(max = 500, message = "{taskUpdate.comment.size}")
    private String comment;

    @NotNull(message = "{taskUpdate.status.notnull}")
    private String status;

    @NotNull(message = "{taskUpdate.updatedBy.notnull}")
    private Integer updatedBy;

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
    
    
} 