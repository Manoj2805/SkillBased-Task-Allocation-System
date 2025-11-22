package com.taskallocation.projectTaskAllocation.entity;

import com.taskallocation.projectTaskAllocation.dto.ImportanceLevel;

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
public class TaskSkill {

    @EmbeddedId // Use the composite key as an embedded field
    private TaskSkillId id;

    @Enumerated(EnumType.STRING)
    private ImportanceLevel importanceLevel;
    
    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skill skill;

	public TaskSkillId getId() {
		return id;
	}

	public void setId(TaskSkillId id) {
		this.id = id;
	}

	public ImportanceLevel getImportanceLevel() {
		return importanceLevel;
	}

	public void setImportanceLevel(ImportanceLevel importanceLevel) {
		this.importanceLevel = importanceLevel;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
    
    
}
