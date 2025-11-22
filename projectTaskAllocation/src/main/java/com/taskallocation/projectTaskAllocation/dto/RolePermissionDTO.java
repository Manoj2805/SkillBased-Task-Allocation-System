package com.taskallocation.projectTaskAllocation.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RolePermissionDTO {
	 @NotNull(message = "{rolePermission.roleId.notnull}")
	    private Integer roleId;

	    @NotNull(message = "{rolePermission.permissionId.notnull}")
	    private Integer permissionId;

	    @NotNull(message = "{rolePermission.grantedBy.notnull}")
	    private Integer grantedBy;

	    @NotNull(message = "{rolePermission.grantedAt.notnull}")
	    private LocalDateTime grantedAt;

		public Integer getRoleId() {
			return roleId;
		}

		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}

		public Integer getPermissionId() {
			return permissionId;
		}

		public void setPermissionId(Integer permissionId) {
			this.permissionId = permissionId;
		}

		public Integer getGrantedBy() {
			return grantedBy;
		}

		public void setGrantedBy(Integer grantedBy) {
			this.grantedBy = grantedBy;
		}

		public LocalDateTime getGrantedAt() {
			return grantedAt;
		}

		public void setGrantedAt(LocalDateTime grantedAt) {
			this.grantedAt = grantedAt;
		}
	    
	    
}
