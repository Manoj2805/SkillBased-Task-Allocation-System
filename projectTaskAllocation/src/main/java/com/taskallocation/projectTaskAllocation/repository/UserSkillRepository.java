package com.taskallocation.projectTaskAllocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taskallocation.projectTaskAllocation.dto.UserSkillDTO;
import com.taskallocation.projectTaskAllocation.entity.Skill;
import com.taskallocation.projectTaskAllocation.entity.User;
import com.taskallocation.projectTaskAllocation.entity.UserSkill;
import com.taskallocation.projectTaskAllocation.entity.UserSkillId;

@Repository
public interface UserSkillRepository extends CrudRepository<UserSkill, UserSkillId> {
    List<UserSkill> findByIdUserId(Integer userId);
    List<UserSkill> findByIdSkillId(Integer skillId);
    List<UserSkill> findByUser(User user);
    List<UserSkill> findBySkill(Skill skill);
    UserSkill findByIdUserIdAndIdSkillId(Integer userId, Integer skillId);
    @Query("SELECT new com.taskallocation.projectTaskAllocation.dto.UserSkillDTO(us.id.userId, us.id.skillId, us.proficiencyLevel, us.lastUsed, s.skillName) " +
    	       "FROM UserSkill us JOIN Skill s ON us.id.skillId = s.skillId")
    	List<UserSkillDTO> findAllUserSkillsWithSkillName();

    @Transactional
    @Modifying
    @Query("DELETE FROM UserSkill us WHERE us.skill.skillId = :skillId")
    void deleteAllBySkillId(@Param("skillId") Integer skillId);
}


