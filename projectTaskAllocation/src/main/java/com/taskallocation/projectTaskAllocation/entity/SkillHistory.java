package com.taskallocation.projectTaskAllocation.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "skill_history")
public class SkillHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer historyId;

	@ManyToOne
	// @JsonIgnore
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	// @JsonIgnore
	@JoinColumn(name = "skill_id")
	private Skill skill;

	private LocalDate acquiredOn;
	private String remarks;

	public SkillHistory() {

	}

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
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
	
	
}
