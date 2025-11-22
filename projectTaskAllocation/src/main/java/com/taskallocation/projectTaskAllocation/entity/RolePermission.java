package com.taskallocation.projectTaskAllocation.entity;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class RolePermission {

    @EmbeddedId
    private RolePermissionId id;

    private Integer grantedBy;
    private LocalDateTime grantedAt;
	public RolePermissionId getId() {
		return id;
	}
	public void setId(RolePermissionId id) {
		this.id = id;
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