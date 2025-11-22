package com.taskallocation.projectTaskAllocation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PermissionDTO {
//	private Integer permissionId;
//	@NotBlank(message = "Permission name is required")
//    @Size(max = 100, message = "Permission name must not exceed 100 characters")
//    private String permissionName;
//    @Size(max = 255, message = "Description must not exceed 255 characters")
//    private String description;
//    
    private Integer permissionId;

    @NotBlank(message = "{permission.name.required}")
    @Size(max = 100, message = "{permission.name.size}")
    private String permissionName;

    @Size(max = 255, message = "{permission.description.size}")
    private String description;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}
