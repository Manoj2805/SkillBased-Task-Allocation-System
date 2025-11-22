package com.taskallocation.projectTaskAllocation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskallocation.projectTaskAllocation.dto.UserSkillDTO;
import com.taskallocation.projectTaskAllocation.entity.UserSkill;
import com.taskallocation.projectTaskAllocation.entity.UserSkillId;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.UserSkillRepository;

@Service
public class UserSkillServiceImpl implements UserSkillService {

    @Autowired
    private UserSkillRepository userSkillRepository;

    private UserSkillDTO mapToDTO(UserSkill userSkill) {
        UserSkillDTO dto = new UserSkillDTO(null, null, null, null, null);
        dto.setUserId(userSkill.getId().getUserId());
        dto.setSkillId(userSkill.getId().getSkillId());
        dto.setProficiencyLevel(userSkill.getProficiencyLevel());
        dto.setLastUsed(userSkill.getLastUsed());
        return dto;
    }
     private UserSkill mapToEntity(UserSkillDTO dto) {
        UserSkill userSkill = new UserSkill();
        UserSkillId id = new UserSkillId();
        id.setUserId(dto.getUserId());
        id.setSkillId(dto.getSkillId());
        userSkill.setId(id);
        userSkill.setProficiencyLevel(dto.getProficiencyLevel());
        userSkill.setLastUsed(dto.getLastUsed());
        return userSkill;
    }
public UserSkillDTO addUserSkill(UserSkillDTO dto) throws UserNotFoundException{
        UserSkill entity = mapToEntity(dto);
        return mapToDTO(userSkillRepository.save(entity));
    }

   
    public UserSkillDTO updateUserSkill(UserSkillDTO dto) throws UserNotFoundException{
        UserSkill existing = userSkillRepository.findByIdUserIdAndIdSkillId(dto.getUserId(), dto.getSkillId());
        if (existing == null) throw new UserNotFoundException("UserSkill not found.");
        existing.setProficiencyLevel(dto.getProficiencyLevel());
        existing.setLastUsed(dto.getLastUsed());
        return mapToDTO(userSkillRepository.save(existing));
    }

    public UserSkillDTO getUserSkill(Integer userId, Integer skillId) throws UserNotFoundException{
        UserSkill userSkill = userSkillRepository.findByIdUserIdAndIdSkillId(userId, skillId);
        if (userSkill == null) throw new UserNotFoundException("UserSkill not found.");
        return mapToDTO(userSkill);
    }
public List<UserSkillDTO> getSkillsByUserId(Integer userId) throws UserNotFoundException {
        List<UserSkill> skills = userSkillRepository.findByIdUserId(userId);
        if (skills == null || skills.isEmpty()) {
            throw new UserNotFoundException("No skills found for user with ID " + userId);
        }
        return skills.stream()
                     .map(this::mapToDTO)
                     .collect(Collectors.toList());
    }
 public List<UserSkillDTO> getUsersBySkillId(Integer skillId) throws UserNotFoundException {
        List<UserSkill> userSkills = userSkillRepository.findByIdSkillId(skillId);
        
        // If no users found for the given skillId, throw an exception
        if (userSkills == null || userSkills.isEmpty()) {
            throw new UserNotFoundException("No users found for skill ID: " + skillId);
        }
        
        return userSkills.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public List<UserSkillDTO> getAllUserSkills() {
//        List<UserSkill> allUserSkills = (List<UserSkill>) userSkillRepository.findAll();
//        return allUserSkills.stream()
//                            .map(this::mapToDTO)
//                            .collect(Collectors.toList());
    	 return userSkillRepository.findAllUserSkillsWithSkillName();
    }


}
