package com.taskallocation.projectTaskAllocation.entity;

import java.time.LocalDate;

import com.taskallocation.projectTaskAllocation.dto.ProficiencyLevel;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Data
@Entity
public class UserSkill {

	@EmbeddedId
	private UserSkillId id;

	@ManyToOne
	@MapsId("userId") // matches field name in UserSkillId
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@MapsId("skillId") // matches field name in UserSkillId
	@JoinColumn(name = "skill_id")
	private Skill skill;

	@Enumerated(EnumType.STRING)
	private ProficiencyLevel proficiencyLevel;

	private LocalDate lastUsed;

	public UserSkillId getId() {
		return id;
	}

	public void setId(UserSkillId id) {
		this.id = id;
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

	public ProficiencyLevel getProficiencyLevel() {
		return proficiencyLevel;
	}

	public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
		this.proficiencyLevel = proficiencyLevel;
	}

	public LocalDate getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(LocalDate lastUsed) {
		this.lastUsed = lastUsed;
	}
	
	
}

