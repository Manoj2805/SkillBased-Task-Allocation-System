package com.taskallocation.projectTaskAllocation.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.dto.ReportStatus;
import com.taskallocation.projectTaskAllocation.entity.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {
    List<Report> findByStatus(ReportStatus status);
    List<Report> findByReportDateBetween(LocalDate start, LocalDate end);
    List<Report> findByCreatedByUserId(Integer userId);
    List<Report> findByTaskTaskId(Integer taskId);
}
