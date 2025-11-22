package com.taskallocation.projectTaskAllocation.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserSkillDTO {
	@NotNull(message = "{userSkill.userId.notnull}")
    private Integer userId;

    @NotNull(message = "{userSkill.skillId.notnull}")
    private Integer skillId;

    @NotNull(message = "{userSkill.proficiencyLevel.notnull}")
    private ProficiencyLevel proficiencyLevel;

    @NotNull(message = "{userSkill.lastUsed.notnull}")
    private LocalDate lastUsed;
    private String skillName;  // Add this field

    // Constructor with skillName
    public UserSkillDTO(Integer userId, Integer skillId, @NotNull(message = "{userSkill.proficiencyLevel.notnull}") ProficiencyLevel proficiencyLevel, LocalDate lastUsed, String skillName) {
        this.userId = userId;
        this.skillId = skillId;
        this.proficiencyLevel = proficiencyLevel;
        this.lastUsed = lastUsed;
        this.skillName = skillName;
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

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
    
    
    
}