package com.taskallocation.projectTaskAllocation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TeamGroupDTO {
	
	 @NotNull(message = "{teamGroup.teamId.notnull}")
	    private Integer teamId;

	    @NotBlank(message = "{teamGroup.teamName.notblank}")
	    @Size(max = 100, message = "{teamGroup.teamName.size}")
	    private String teamName;

	    @NotBlank(message = "{teamGroup.projectName.notblank}")
	    @Size(max = 100, message = "{teamGroup.projectName.size}")
	    private String projectName;

	    @NotNull(message = "{teamGroup.createdBy.notnull}")
	    private Integer createdBy;

		public Integer getTeamId() {
			return teamId;
		}

		public void setTeamId(Integer teamId) {
			this.teamId = teamId;
		}

		public String getTeamName() {
			return teamName;
		}

		public void setTeamName(String teamName) {
			this.teamName = teamName;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public Integer getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(Integer createdBy) {
			this.createdBy = createdBy;
		}
	    
}