package com.taskallocation.projectTaskAllocation.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.dto.ReportStatus;
import com.taskallocation.projectTaskAllocation.dto.returnReportDTO;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.ReportServiceImpl;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

	@Autowired
	private ReportServiceImpl reportService;

	@GetMapping("/status/{status}")
	public ResponseEntity<List<returnReportDTO>> getReportsByStatus(@PathVariable ReportStatus status) {
		try {
			List<returnReportDTO> reports = reportService.getReportsByStatus(status);
			return new ResponseEntity<>(reports, HttpStatus.OK);
		} catch (UserNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
   
     @GetMapping("/dateRange")
     public ResponseEntity<List<returnReportDTO>> getReportsByDateRange(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) throws UserNotFoundException {
            List<returnReportDTO> reports = reportService.getReportsByDateRange(start, end);
             return ResponseEntity.ok(reports); // Returns 200 OK with the list of reports
}
//http://localhost:8000/api/reports/dateRange?start=2025-05-10&end=2025-05-13
@GetMapping("/user/{userId}")
	public ResponseEntity<List<returnReportDTO>> getReportsByUser(@PathVariable Integer userId) {
		try {
			List<returnReportDTO> reports = reportService.getReportsByUser(userId);
			return new ResponseEntity<>(reports, HttpStatus.OK);
		} catch (UserNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}