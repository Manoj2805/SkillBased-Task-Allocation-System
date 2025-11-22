package com.taskallocation.projectTaskAllocation.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taskallocation.projectTaskAllocation.dto.ReportStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
//@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="task_id")
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="user_id")
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    private Integer progressPercent;

    private String remarks;

    private LocalDate reportDate;
    @Override
    public String toString() {
        return "Report{" +
               "reportId=" + reportId +
               ", status='" + status + '\'' +
               ", progressPercent=" + progressPercent +
               ", remarks='" + remarks + '\'' +
               ", reportDate=" + reportDate +
               // Safely print related IDs, not entire objects
               (task != null ? ", taskId=" + task.getTaskId() : "") +
               (createdBy != null ? ", createdByUserId=" + createdBy.getUserId() : "") +
               '}';
    }
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
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
    
    
//	public Report(Object reportId2, Task mockTask, ReportStatus status2, int i, String string, LocalDate date1,
//			User mockUser) {
//		// TODO Auto-generated constructor stub
//		
//	}
    
    
}
