package com.taskallocation.projectTaskAllocation.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class TaskSkillId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer taskId;
    private Integer skillId;

    // Default constructor
    public TaskSkillId() {}

    public TaskSkillId(Integer taskId, Integer skillId) {
        this.taskId = taskId;
        this.skillId = skillId;
    }

    // Getters and setters
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    // hashCode and equals methods for composite key comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskSkillId that = (TaskSkillId) o;
        return Objects.equals(taskId, that.taskId) &&
               Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, skillId);
    }
    
    
}
