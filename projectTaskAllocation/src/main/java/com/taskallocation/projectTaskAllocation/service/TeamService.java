package com.taskallocation.projectTaskAllocation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskallocation.projectTaskAllocation.dto.TaskAssignmentRequest;
import com.taskallocation.projectTaskAllocation.dto.TaskStatus;
import com.taskallocation.projectTaskAllocation.dto.TeamMemberDTO;
import com.taskallocation.projectTaskAllocation.entity.Skill;
import com.taskallocation.projectTaskAllocation.entity.Task;
import com.taskallocation.projectTaskAllocation.entity.TeamGroup;
import com.taskallocation.projectTaskAllocation.entity.TeamMember;
import com.taskallocation.projectTaskAllocation.entity.TeamMemberId;
import com.taskallocation.projectTaskAllocation.entity.User;
import com.taskallocation.projectTaskAllocation.repository.SkillRepository;
import com.taskallocation.projectTaskAllocation.repository.TaskRepository;
import com.taskallocation.projectTaskAllocation.repository.TeamGroupRepository;
import com.taskallocation.projectTaskAllocation.repository.TeamMemberRepository;
import com.taskallocation.projectTaskAllocation.repository.UserRepository;
import com.taskallocation.projectTaskAllocation.repository.UserSkillRepository;

@Service
public class TeamService {

	@Autowired
	private TeamGroupRepository teamGroupRepository;

	

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserSkillRepository userSkillRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
		
	@Autowired 
	private TeamMemberRepository teamRepository;
	
	public TeamGroup createTeam(TeamGroup teamGroup) {
		return teamGroupRepository.save(teamGroup);
	}
        
        public TeamMember addUserToTeam(TeamMemberDTO dto) {
                if (dto.getUserId() == null || dto.getTeamId() == null || 
	        dto.getTaskId() == null || dto.getSkillId() == null) {
	        throw new IllegalArgumentException("All IDs must be provided");
	    }
             User user = userRepository.findById(dto.getUserId())
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    Task task = taskRepository.findById(dto.getTaskId())
	        .orElseThrow(() -> new RuntimeException("Task not found"));

	    Skill skill = skillRepository.findById(dto.getSkillId())
	        .orElseThrow(() -> new RuntimeException("Skill not found"));
             TeamMemberId memberId = new TeamMemberId(dto.getUserId(), dto.getTeamId());

	    TeamMember member = new TeamMember();
	    member.setTeamMemberId(memberId);
	    member.setUser(user);
	    member.setSkill(skill);
	    member.setTask(task);
	    member.setJoinedOn(dto.getJoinedOn());
	    member.setRoleInTeam(dto.getRoleInTeam());

	    return teamRepository.save(member);
	}
        public List<TeamMemberDTO> getAllTeamMembers() {
	    List<TeamMember> members = (List<TeamMember>) teamRepository.findAll(); // Get all records
	    return members.stream()
	                  .map(this::convertToDTO)
	                  .collect(Collectors.toList());
	}
        	public void assignTaskToMember(TaskAssignmentRequest request) throws Exception {
	    TeamMember member = teamRepository.findByTeamMemberId_UserIdAndTeamMemberId_TeamId(
	        request.getAssignedToUserId(), request.getTeamId()
	    );

	    if (member == null) {
	        throw new Exception("Team member not found");
	    }
            Task newTask = new Task();
	    newTask.setTitle(request.getTaskName());
	    newTask.setDescription(request.getTaskDescription());
	    newTask.setAssignedTo(member.getUser()); // assuming relationship
	    newTask.setStatus(TaskStatus.ASSIGNED);
	    newTask.setPriority(request.getPriority());
	    newTask.setDeadline(request.getDeadline());
	    newTask.setEstimatedHours(request.getEstimatedHours());
	    newTask.setActualHours(request.getActualHours());
	    newTask.setStartDate(request.getStartDate());
	    newTask.setEndDate(request.getEndDate());
              taskRepository.save(newTask);
	    
	    // Update team member’s task ID
	    member.setTaskId(newTask.getTaskId());
	    teamRepository.save(member);
}
private TeamMemberDTO convertToDTO(TeamMember member) {
		TeamMemberDTO dto = new TeamMemberDTO();
		User user = member.getUser();

		dto.setUserId(user.getUserId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setRoleInTeam(member.getRoleInTeam());
		dto.setSkills(getSkills(user));
		dto.setTasks(getTasks(user));
		dto.setWorkload(calculateWorkload(user));

		return dto;
	}
private List<String> getSkills(User user) {
		return userSkillRepository.findByUser(user).stream().map(userSkill -> userSkill.getSkill().getSkillName())
				.collect(Collectors.toList());
	}

	private List<String> getTasks(User user) {
		return taskRepository.findByAssignedTo_UserId(user.getUserId()).stream().map(Task::getTitle)
				.collect(Collectors.toList());
	}
private String calculateWorkload(User user) {
		List<Task> tasks = taskRepository.findByAssignedTo_UserId(user.getUserId());

		if (tasks.isEmpty()) {
			return "0%";
		}

		double totalEstimated = tasks.stream().mapToDouble(Task::getEstimatedHours).sum();

		double totalActual = tasks.stream().filter(task -> task.getActualHours() != null)
				.mapToDouble(Task::getActualHours).sum();

		if (totalEstimated == 0) {
			return "0%";
		}

		int workload = (int) Math.min((totalActual / totalEstimated) * 100, 100);
		return workload + "%";
	}
}