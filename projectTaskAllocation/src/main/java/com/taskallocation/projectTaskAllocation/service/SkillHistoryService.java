package com.taskallocation.projectTaskAllocation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskallocation.projectTaskAllocation.entity.SkillHistory;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;
import com.taskallocation.projectTaskAllocation.repository.SkillHistoryRepository;

@Service
public class SkillHistoryService {
    @Autowired
    private SkillHistoryRepository skillHistoryRepository;
    public SkillHistory logSkillAcquisition(SkillHistory skillHistory) throws UserNotFoundException{
        return skillHistoryRepository.save(skillHistory);
    }

    public List<SkillHistory> getSkillHistoryByUser(Integer userId) throws UserNotFoundException{
        return skillHistoryRepository.findByUserUserId(userId);
    }
}