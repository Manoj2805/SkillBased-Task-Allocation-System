package com.taskallocation.projectTaskAllocation.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleDTO {
	private Integer roleId;

    @NotNull(message = "{role.roleName.notnull}")
    @NotBlank(message = "{role.roleName.notblank}")
    @Size(max = 100, message = "{role.roleName.size}")
    private String roleName;

    @Size(max = 255, message = "{role.description.size}")
    private String description;
    
    private Long userCount;
	private List<String> userNames;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getUserCount() {
		return userCount;
	}
	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}
	public List<String> getUserNames() {
		return userNames;
	}
	public void setUserNames(List<String> userNames) {
		this.userNames = userNames;
	}
	
	
}
