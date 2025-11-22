package com.taskallocation.projectTaskAllocation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NotificationDTO {
//	private Integer notificationId;
//	@NotNull(message="userId is mandatory")
//    private Integer userId;
//    private String message;
//    private String type;
//    private Boolean isRead;
	private Integer notificationId;

    @NotNull(message = "{notification.userId.notnull}")
    private Integer userId;

    @NotBlank(message = "{notification.message.notblank}")
    @Size(max = 255, message = "{notification.message.size}")
    private String message;

    @NotBlank(message = "{notification.type.notblank}")
    private String type;

    private Boolean isRead;

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
    
    
    
}