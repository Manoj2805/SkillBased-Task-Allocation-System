package com.taskallocation.projectTaskAllocation.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SkillDTO {

    private Integer skillId;

    @NotNull(message = "{skill.skillName.notnull}")
    @Size(min = 1, max = 100, message = "{skill.skillName.size}")
    private String skillName;

    @Size(max = 255, message = "{skill.skillDescription.size}")
    private String skillDescription;

    @Size(max = 50, message = "{skill.category.size}")
    private String category;

    @NotNull(message = "{skill.level.notnull}")
    private SkillLevel level;

    @NotNull(message = "{skill.createdAt.notnull}")
    private LocalDateTime createdAt;  	    
    
    private Integer userId;
    
    public SkillDTO(Integer skillId, String skillName, String skillDescription, String category, SkillLevel level, LocalDateTime createdAt) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
        this.category = category;
        this.level = level;
        this.createdAt = createdAt;
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

	public String getSkillDescription() {
		return skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public SkillLevel getLevel() {
		return level;
	}

	public void setLevel(SkillLevel level) {
		this.level = level;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
    
}
