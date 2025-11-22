package com.taskallocation.projectTaskAllocation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskallocation.projectTaskAllocation.dto.ProficiencyLevel;
import com.taskallocation.projectTaskAllocation.dto.SkillDTO;
import com.taskallocation.projectTaskAllocation.entity.Skill;
import com.taskallocation.projectTaskAllocation.entity.User;
import com.taskallocation.projectTaskAllocation.entity.UserSkill;
import com.taskallocation.projectTaskAllocation.entity.UserSkillId;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.SkillRepository;
import com.taskallocation.projectTaskAllocation.repository.UserRepository;
import com.taskallocation.projectTaskAllocation.repository.UserSkillRepository;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;
//    
    @Autowired
    private UserSkillRepository userSkillRepository;
    
    @Autowired
    private UserRepository userRepository;
public SkillDTO addSkill(SkillDTO skillDTO) throws UserNotFoundException{

         Skill skill = new Skill();
    	    LocalDateTime now = LocalDateTime.now();

    	    // Populate Skill entity
    	    skill.setSkillName(skillDTO.getSkillName());
    	    skill.setSkillDescription(skillDTO.getSkillDescription());
    	    skill.setCategory(skillDTO.getCategory());
    	    skill.setLevel(skillDTO.getLevel());
    	    skill.setCreatedAt(now);
            Skill savedSkill = skillRepository.save(skill);

    	    // Associate with user if userId is provided
    	    if (skillDTO.getUserId() != null) {
    	        Optional<User> userOpt = userRepository.findById(skillDTO.getUserId());

    	        if (userOpt.isEmpty()) {
    	            throw new UserNotFoundException("User not found with id " + skillDTO.getUserId());
    	        }

    	        UserSkill userSkill = new UserSkill();
                 UserSkillId userSkillId = new UserSkillId();
    	        userSkillId.setUserId(userOpt.get().getUserId());
    	        userSkillId.setSkillId(savedSkill.getSkillId());

    	        userSkill.setId(userSkillId);
    	        userSkill.setUser(userOpt.get());
    	        userSkill.setSkill(savedSkill);
//    	        userSkill.setProficiencyLevel(skillDTO.getLevel());  // Optional: map to a more accurate field
    	        userSkill.setProficiencyLevel(ProficiencyLevel.valueOf(skillDTO.getLevel().name()));
    	        userSkill.setLastUsed(LocalDate.now());

    	        // Save user-skill association
    	        userSkillRepository.save(userSkill);
    	    }
              return mapTODTO(savedSkill);
    }

    public Skill updateSkill(Integer id, SkillDTO updatedSkill) throws UserNotFoundException{

               Skill skill = skillRepository.findById(id)
    	        .orElseThrow(() -> new UserNotFoundException("Skill ID not found"));

    	    // Update Skill fields
    	    skill.setSkillName(updatedSkill.getSkillName());
    	    skill.setSkillDescription(updatedSkill.getSkillDescription());
    	    skill.setCategory(updatedSkill.getCategory());
    	    skill.setLevel(updatedSkill.getLevel());
            Skill savedSkill = skillRepository.save(skill);

    	    // Update related UserSkill proficiency levels
    	    List<UserSkill> userSkills = userSkillRepository.findBySkill(skill);

    	    for (UserSkill userSkill : userSkills) {
    	        // Convert SkillLevel to ProficiencyLevel
    	        userSkill.setProficiencyLevel(ProficiencyLevel.valueOf(updatedSkill.getLevel().name()));
    	        userSkillRepository.save(userSkill);
    	    }
               return savedSkill;
    }

    public List<Skill> getAllSkills() throws UserNotFoundException{
        return (List<Skill>) skillRepository.findAll();
    }
    
//    @Override
    public List<SkillDTO> findSkillsByUserId(Integer userId) {
        return skillRepository.findSkillsByUserId(userId);
    }
    public List<Skill> getBySkillName(String name) throws UserNotFoundException{
        return skillRepository.findAllBySkillName(name);
    }
    
    @Override
    @Transactional
    public void deleteSkill(Integer id) throws UserNotFoundException {
                Optional<Skill> skillOptional = skillRepository.findById(id);
        if (skillOptional.isEmpty()) {
            throw new UserNotFoundException("Skill with ID " + id + " not found");
        }

        // First remove all references in user_skill table
        userSkillRepository.deleteAllBySkillId(id);

        // Then delete the skill
        skillRepository.deleteById(id);
    }
    private SkillDTO mapTODTO(Skill skill) throws UserNotFoundException{
    	SkillDTO dto=new SkillDTO(null, null, null, null, null, null);
    	dto.setCategory(skill.getCategory());
    	dto.setCreatedAt(skill.getCreatedAt());
    	dto.setLevel(skill.getLevel());
    	dto.setSkillDescription(skill.getSkillDescription());
    	dto.setSkillId(skill.getSkillId());
    	dto.setSkillName(skill.getSkillName());
    	dto.setCreatedAt(LocalDateTime.now());
    	return dto;
    	
    }
    @Override
	public List<SkillDTO> findSkillsByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
