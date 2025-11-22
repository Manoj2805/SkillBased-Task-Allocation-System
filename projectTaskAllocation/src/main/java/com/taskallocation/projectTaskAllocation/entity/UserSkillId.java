package com.taskallocation.projectTaskAllocation.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserSkillId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer skillId;

	public UserSkillId() {
	}

	public UserSkillId(Integer userId, Integer skillId) {
		this.userId = userId;
		this.skillId = skillId;
	}

	// Getters and Setters (or use Lombok if preferred)

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UserSkillId))
			return false;
		UserSkillId that = (UserSkillId) o;
		return Objects.equals(userId, that.userId) && Objects.equals(skillId, that.skillId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, skillId);
	}
}
