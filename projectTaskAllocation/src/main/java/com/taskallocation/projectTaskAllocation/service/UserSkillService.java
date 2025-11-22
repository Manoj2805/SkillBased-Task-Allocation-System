package com.taskallocation.projectTaskAllocation.service;

import java.util.List;

import com.taskallocation.projectTaskAllocation.dto.UserSkillDTO;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;

public interface UserSkillService {
    UserSkillDTO addUserSkill(UserSkillDTO userSkillDTO) throws UserNotFoundException;
    UserSkillDTO updateUserSkill(UserSkillDTO userSkillDTO) throws UserNotFoundException;
    UserSkillDTO getUserSkill(Integer userId, Integer skillId) throws UserNotFoundException;
    List<UserSkillDTO> getSkillsByUserId(Integer userId)  throws UserNotFoundException;
    List<UserSkillDTO> getUsersBySkillId(Integer skillId) throws UserNotFoundException;
    List<UserSkillDTO> getAllUserSkills() throws UserNotFoundException;
}
