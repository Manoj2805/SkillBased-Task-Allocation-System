package com.taskallocation.projectTaskAllocation.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReportDTO {
public ReportDTO(Integer reportId2, ReportStatus status2, Integer progressPercent2, String remarks2,
			LocalDate reportDate2, TaskDTO taskDTO, UserDTO createdByDTO) {
		// TODO Auto-generated constructor stub
	}

	////	private Integer reportId;
////    private Integer taskId;
////    private ReportStatus status;
////    private Integer progressPercent;
////    private String remarks;
////    private LocalDate reportDate;
////    private Integer createdBy;
//	
	private Integer reportId;

    @NotNull(message = "{report.taskId.notnull}")
    private Integer taskId;

    @NotNull(message = "{report.status.notnull}")
    private ReportStatus status;

    @Min(value = 0, message = "{report.progressPercent.min}")
    @Max(value = 100, message = "{report.progressPercent.max}")
    private Integer progressPercent;

    @Size(max = 500, message = "{report.remarks.size}")
    private String remarks;

    @NotNull(message = "{report.reportDate.notnull}")
    private LocalDate reportDate;

    @NotNull(message = "{report.createdBy.notnull}")
    private Integer createdBy;

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public ReportStatus getStatus() {
		return status;
	}

	public void setStatus(ReportStatus status) {
		this.status = status;
	}

	public Integer getProgressPercent() {
		return progressPercent;
	}

	public void setProgressPercent(Integer progressPercent) {
		this.progressPercent = progressPercent;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDate getReportDate() {
		return reportDate;
	}

	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
    
    
}
