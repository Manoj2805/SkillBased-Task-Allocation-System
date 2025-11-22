package com.taskallocation.projectTaskAllocation.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.dto.TaskAssignmentRequest;
import com.taskallocation.projectTaskAllocation.dto.TeamMemberDTO;
import com.taskallocation.projectTaskAllocation.entity.TeamGroup;
import com.taskallocation.projectTaskAllocation.entity.TeamMember;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.TeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@PostMapping
	public ResponseEntity<TeamGroup> createTeam(@RequestBody TeamGroup teamGroup) throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(teamService.createTeam(teamGroup));
	}
@PostMapping("/addUser")
	public ResponseEntity<Map<String, Object>> addUserToTeam(@RequestBody TeamMemberDTO dto)
			throws UserNotFoundException {
		TeamMember saved = teamService.addUserToTeam(dto);

		Map<String, Object> response = new HashMap<>();
		response.put("message", "User added to team successfully");
		response.put("teamMemberId", saved.getTeamMemberId());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
@GetMapping("/members")
	public List<TeamMemberDTO> getAllTeamMembers() {
		return teamService.getAllTeamMembers();
	}

	@PostMapping("/assign-task")
	public ResponseEntity<?> assignTask(@RequestBody TaskAssignmentRequest request) {
		try {
			teamService.assignTaskToMember(request);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to assign task");
		}
	}
}