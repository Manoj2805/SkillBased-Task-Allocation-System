package com.taskallocation.projectTaskAllocation.service;

import java.time.LocalDate;
import java.util.List;

import com.taskallocation.projectTaskAllocation.dto.ReportStatus;
import com.taskallocation.projectTaskAllocation.dto.returnReportDTO;
import com.taskallocation.projectTaskAllocation.entity.Report;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;

public interface ReportService {
	List<Report> getReportsByStatus(ReportStatus status) throws UserNotFoundException;
	List<returnReportDTO> getReportsByDateRange(LocalDate start, LocalDate end) throws UserNotFoundException;

}
