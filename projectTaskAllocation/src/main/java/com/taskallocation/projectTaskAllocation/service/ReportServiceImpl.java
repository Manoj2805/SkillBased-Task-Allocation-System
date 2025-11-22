package com.taskallocation.projectTaskAllocation.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskallocation.projectTaskAllocation.dto.ReportStatus;
import com.taskallocation.projectTaskAllocation.dto.returnReportDTO;
import com.taskallocation.projectTaskAllocation.entity.Report;
import com.taskallocation.projectTaskAllocation.entity.Task;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.ReportRepository;
import com.taskallocation.projectTaskAllocation.repository.TaskRepository;

@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List getReportsByStatus(ReportStatus status) throws UserNotFoundException{
    	 List<Report> reports = reportRepository.findByStatus(status);
    	 List<returnReportDTO> returnReportDTOs=new ArrayList<>();
       	 for(Report report:reports) {
           	 Task task=taskRepository.findById(report.getTask().getTaskId()).orElseThrow(()->new RuntimeException("Task Not Found with taskId : "+report.getTask().getTaskId()));
           	 returnReportDTO DTO=new returnReportDTO();
           	 DTO.setDeadline(task.getDeadline());
           	 DTO.setEndDate(task.getEndDate());
           	 DTO.setPriority(task.getPriority());
           	 DTO.setTitle(task.getTitle());
           	 DTO.setStartDate(task.getStartDate());
           	 DTO.setStatus(task.getStatus());
           	 DTO.setProgressPercent(report.getProgressPercent());
           	 DTO.setRemarks(report.getRemarks());
           	 DTO.setReportDate(report.getReportDate());
           	 DTO.setReportId(report.getReportId());
           	 DTO.setReportStatus(report.getStatus());
           	 DTO.setUserName(task.getAssignedTo().getName());
           	 DTO.setUserId(task.getAssignedTo().getUserId());
           	 DTO.setEmail(task.getAssignedTo().getEmail());
           	 DTO.setUserStatus(task.getAssignedTo().getStatus());
           	 DTO.setRoleId(task.getAssignedTo().getRole().getRoleId());
           	 DTO.setRoleName(task.getAssignedTo().getRole().getRoleName());
           	 returnReportDTOs.add(DTO);
       	 }
    	    if (reports == null || reports.isEmpty()) {
    	        throw new UserNotFoundException("No reports found with status: " + status);
    	    }
    	    return returnReportDTOs;
    }

 public List<returnReportDTO> getReportsByUser(Integer userId) throws UserNotFoundException {
            List<Report> reports = reportRepository.findByCreatedByUserId(userId);
            List<returnReportDTO> returnReportDTOs=new ArrayList<>();
       	 for(Report report:reports) {
Task task=taskRepository.findById(report.getTask().getTaskId()).orElseThrow(()->new RuntimeException("Task Not Found with taskId : "+report.getTask().getTaskId()));
           	 returnReportDTO DTO=new returnReportDTO();
           	 DTO.setDeadline(task.getDeadline());
           	 DTO.setEndDate(task.getEndDate());
                 DTO.setPriority(task.getPriority());
           	 DTO.setTitle(task.getTitle());
           	 DTO.setStartDate(task.getStartDate());
           	 DTO.setStatus(task.getStatus());
           	 DTO.setProgressPercent(report.getProgressPercent());
           	 DTO.setRemarks(report.getRemarks());
           	 DTO.setReportDate(report.getReportDate());
                 DTO.setReportId(report.getReportId());
           	 DTO.setReportStatus(report.getStatus());
           	 DTO.setUserName(task.getAssignedTo().getName());
           	 DTO.setUserId(task.getAssignedTo().getUserId());
                 DTO.setEmail(task.getAssignedTo().getEmail());
           	 DTO.setUserStatus(task.getAssignedTo().getStatus());
           	 DTO.setRoleId(task.getAssignedTo().getRole().getRoleId());
           	 DTO.setRoleName(task.getAssignedTo().getRole().getRoleName());
                 returnReportDTOs.add(DTO);
       	 }
            if (reports == null || reports.isEmpty()) {
                throw new UserNotFoundException("No reports found for user with ID: " + userId);
            }
            return returnReportDTOs;
}
public List<returnReportDTO> getReportsByDateRange(LocalDate start, LocalDate end) throws UserNotFoundException{
    	 List<Report> reports = reportRepository.findByReportDateBetween(start, end);
         List<returnReportDTO> returnReportDTOs=new ArrayList<>();
    	 for(Report report:reports) {
             Task task=taskRepository.findById(report.getTask().getTaskId()).orElseThrow(()->new RuntimeException("Task Not Found with taskId : "+report.getTask().getTaskId()));
        	 returnReportDTO DTO=new returnReportDTO();
        	 DTO.setDeadline(task.getDeadline());
        	 DTO.setEndDate(task.getEndDate());
                 DTO.setPriority(task.getPriority());
        	 DTO.setTitle(task.getTitle());
        	 DTO.setStartDate(task.getStartDate());
        	 DTO.setStatus(task.getStatus());
                  DTO.setProgressPercent(report.getProgressPercent());
        	 DTO.setRemarks(report.getRemarks());
        	 DTO.setReportDate(report.getReportDate());
        	 DTO.setReportId(report.getReportId());
                  DTO.setReportStatus(report.getStatus());
        	 DTO.setUserName(task.getAssignedTo().getName());
        	 DTO.setUserId(task.getAssignedTo().getUserId());
        	 DTO.setEmail(task.getAssignedTo().getEmail());
                 DTO.setUserStatus(task.getAssignedTo().getStatus());
        	 DTO.setRoleId(task.getAssignedTo().getRole().getRoleId());
        	 DTO.setRoleName(task.getAssignedTo().getRole().getRoleName());
        	 returnReportDTOs.add(DTO);
                 }
    	    if (reports == null || reports.isEmpty()) {
    	        throw new UserNotFoundException("No reports found between " + start + " and " + end);
    	    }
    	    return returnReportDTOs;
    	}
}
