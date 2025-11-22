package com.taskallocation.projectTaskAllocation.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SkillHistoryDTO {
	
	
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public LocalDate getAcquiredOn() {
		return acquiredOn;
	}
	public void setAcquiredOn(LocalDate acquiredOn) {
		this.acquiredOn = acquiredOn;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	private Integer historyId;
	@NotNull(message = "{skillHistory.userId.notnull}")
    private Integer userId;
    @NotNull(message = "{skillHistory.skillId.notnull}")
    private Integer skillId;
    @NotNull(message = "{skillHistory.acquiredOn.notnull}")
    @PastOrPresent(message = "{skillHistory.acquiredOn.pastOrPresent}")
    private LocalDate acquiredOn;
    @Size(max = 255, message = "{skillHistory.remarks.size}")
    private String remarks;
}
