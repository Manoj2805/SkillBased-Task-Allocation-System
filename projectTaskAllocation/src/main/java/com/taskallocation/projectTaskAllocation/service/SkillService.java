package com.taskallocation.projectTaskAllocation.service;

import java.util.List;

import com.taskallocation.projectTaskAllocation.dto.SkillDTO;
import com.taskallocation.projectTaskAllocation.entity.Skill;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;

public interface SkillService {
	SkillDTO addSkill(SkillDTO skill) throws UserNotFoundException;
	Skill updateSkill(Integer id, SkillDTO updatedSkill) throws UserNotFoundException;
	List<Skill> getAllSkills() throws UserNotFoundException;
	List<Skill> getBySkillName(String name) throws UserNotFoundException;
	List<SkillDTO> findSkillsByUserId(Long userId);
    void deleteSkill(Integer id) throws UserNotFoundException;
}