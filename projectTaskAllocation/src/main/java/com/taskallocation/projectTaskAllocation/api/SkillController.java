package com.taskallocation.projectTaskAllocation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.dto.SkillDTO;
import com.taskallocation.projectTaskAllocation.entity.Skill;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.SkillServiceImpl;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:4200") 
public class SkillController {

    @Autowired
    private SkillServiceImpl skillService;

    @PostMapping
    public ResponseEntity<SkillDTO> addSkill(@RequestBody SkillDTO skill) throws UserNotFoundException{
        return ResponseEntity.status(HttpStatus.CREATED).body(skillService.addSkill(skill));
    }
  @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Integer id, @RequestBody SkillDTO skill) throws UserNotFoundException{
        return ResponseEntity.ok(skillService.updateSkill(id, skill));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Integer id) throws UserNotFoundException {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
@GetMapping
    public List<Skill> getAllSkills() throws UserNotFoundException{
        return skillService.getAllSkills();
    }
    
    @GetMapping("/user/{userId}")
    public List<SkillDTO> getSkillsByUser(@PathVariable Integer userId) {
        return skillService.findSkillsByUserId(userId);
    }


    @GetMapping("/{skillName}")
    public List<Skill> getSkillByName(@PathVariable String skillName) throws UserNotFoundException{
    	List<Skill> res=skillService.getBySkillName(skillName);
        return res;
    }
}
