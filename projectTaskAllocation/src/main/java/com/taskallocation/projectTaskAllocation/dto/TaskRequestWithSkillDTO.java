package com.taskallocation.projectTaskAllocation.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskRequestWithSkillDTO {
	private Integer requestId;
    private Integer userId;
    private Integer taskId;
    private String requestMessage;
    private LocalDateTime requestDate;
    private String status;
    private Integer respondedBy;
    private LocalDateTime respondedAt;
    private Integer skillId;
    private String skillName;

    public TaskRequestWithSkillDTO(Integer requestId, Integer userId, Integer taskId,
                                   String requestMessage, LocalDateTime requestDate,
                                   String status, Integer respondedBy, LocalDateTime respondedAt,
                                   Integer skillId, String skillName) {
        this.requestId = requestId;
        this.userId = userId;
        this.taskId = taskId;
        this.requestMessage = requestMessage;
        this.requestDate = requestDate;
        this.status = status;
        this.respondedBy = respondedBy;
        this.respondedAt = respondedAt;
        this.skillId = skillId;
        this.skillName = skillName;
    }

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

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
    
}
