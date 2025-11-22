package com.taskallocation.projectTaskAllocation.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDTO {

    @NotNull(message = "{taskRequest.requestId.notnull}")
    private Integer requestId;

    @NotNull(message = "{taskRequest.userId.notnull}")
    private Integer userId;

    private String username;  // fetched from related User entity

    @NotNull(message = "{taskRequest.taskId.notnull}")
    private Integer taskId;

    @Size(max = 500, message = "{taskRequest.requestMessage.size}")
    private String requestMessage;

    @NotNull(message = "{taskRequest.status.notnull}")
    private RequestStatus status;

    @NotNull(message = "{taskRequest.requestDate.notnull}")
    private LocalDateTime requestDate;

    private Integer respondedBy;

    private LocalDateTime respondedAt;

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	public Integer getRespondedBy() {
		return respondedBy;
	}

	public void setRespondedBy(Integer respondedBy) {
		this.respondedBy = respondedBy;
	}

	public LocalDateTime getRespondedAt() {
		return respondedAt;
	}

	public void setRespondedAt(LocalDateTime respondedAt) {
		this.respondedAt = respondedAt;
	}
    
    
}
