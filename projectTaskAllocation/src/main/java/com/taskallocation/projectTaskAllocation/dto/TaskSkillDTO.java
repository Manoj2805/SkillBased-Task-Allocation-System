package com.taskallocation.projectTaskAllocation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskSkillDTO {
	 @NotNull(message = "{taskSkill.taskId.notnull}")
	    private Integer taskId;
	    
	    @NotNull(message = "{taskSkill.skillId.notnull}")
	    private Integer skillId;
	    
	    @NotBlank(message = "{taskSkill.importanceLevel.notblank}")
	    @Size(max = 50, message = "{taskSkill.importanceLevel.size}")
	    private ImportanceLevel  importanceLevel;

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

		public ImportanceLevel getImportanceLevel() {
			return importanceLevel;
		}

		public void setImportanceLevel(ImportanceLevel importanceLevel) {
			this.importanceLevel = importanceLevel;
		}
	    
	    
}
