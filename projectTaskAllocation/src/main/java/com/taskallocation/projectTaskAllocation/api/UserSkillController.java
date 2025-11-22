package com.taskallocation.projectTaskAllocation.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.dto.UserSkillDTO;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.UserSkillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-skills")
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true") 
public class UserSkillController {

    @Autowired
    private UserSkillService userSkillService;
  @PostMapping
    public ResponseEntity<?> addUserSkill(@Valid @RequestBody UserSkillDTO dto, BindingResult result) throws UserNotFoundException{
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }

        UserSkillDTO saved = userSkillService.addUserSkill(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
@PutMapping
    public ResponseEntity<UserSkillDTO> updateUserSkill(@Valid @RequestBody UserSkillDTO dto) throws UserNotFoundException{
        UserSkillDTO updatedSkill = userSkillService.updateUserSkill(dto);
        return ResponseEntity.ok(updatedSkill);
    }
@GetMapping
    public List<UserSkillDTO> getAllUserSkills() throws UserNotFoundException {
        return userSkillService.getAllUserSkills();
    }

    @GetMapping("/{userId}/{skillId}")
    public UserSkillDTO getUserSkill(@PathVariable Integer userId, @PathVariable Integer skillId) throws UserNotFoundException{
        return userSkillService.getUserSkill(userId, skillId);//completed
        
    }

    @GetMapping("/user/{userId}")
    public List<UserSkillDTO> getSkillsByUserId(@PathVariable Integer userId) throws UserNotFoundException{
        return userSkillService.getSkillsByUserId(userId);//completed
    }
    
    @GetMapping("/skill/{skillId}")
    public List<UserSkillDTO> getUsersBySkillId(@PathVariable Integer skillId) throws UserNotFoundException{
        return userSkillService.getUsersBySkillId(skillId);//completed
    }
}