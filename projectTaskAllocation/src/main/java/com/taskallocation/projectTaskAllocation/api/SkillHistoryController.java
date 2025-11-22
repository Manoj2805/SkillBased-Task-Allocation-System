package com.taskallocation.projectTaskAllocation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskallocation.projectTaskAllocation.entity.SkillHistory;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.service.SkillHistoryService;

@RestController
@RequestMapping("/api/skill-history")
public class SkillHistoryController {

    @Autowired
    private SkillHistoryService skillHistoryService;

    @PostMapping
    public ResponseEntity<SkillHistory> logSkillAcquisition(@RequestBody SkillHistory skillHistory) throws UserNotFoundException{
        return ResponseEntity.status(HttpStatus.CREATED).body(skillHistoryService.logSkillAcquisition(skillHistory));
}
 @GetMapping("/user/{userId}")
    public List<SkillHistory> getSkillHistoryByUser(@PathVariable Integer userId) throws UserNotFoundException{
        return skillHistoryService.getSkillHistoryByUser(userId);
    }
}