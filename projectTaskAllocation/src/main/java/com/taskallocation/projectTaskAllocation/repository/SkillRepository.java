package com.taskallocation.projectTaskAllocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taskallocation.projectTaskAllocation.dto.SkillDTO;
import com.taskallocation.projectTaskAllocation.entity.Skill;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {
    List<Skill> findAllBySkillName(String skillName);
    List<Skill> findByCategory(String category);
    List<Skill> findByLevel(String level);
//    @Query("SELECT new com.taskallocation.projectTaskAllocation.dto.SkillDTO(" +
//    	       "s.skillId, s.skillName, s.skillDescription, s.category, s.level, s.createdAt) " +
//    	       "FROM UserSkill us JOIN us.skill s " +
//    	       "WHERE us.user.userId = :userId")
//
//    	List<SkillDTO> findSkillsByUserId(@Param("userId") Long userId);
//    @Query("SELECT new com.taskallocation.projectTaskAllocation.dto.SkillDTO(" +
//    	       "s.skillId, s.skillName, s.skillDescription, s.category, s.level, s.createdAt) " +
//    	       "FROM UserSkill us JOIN us.skill s " +
//    	       "WHERE us.user.userId = :userId")
//    	List<SkillDTO> findSkillsByUserId(@Param("userId") Integer userId);
//    @Query("SELECT new com.taskallocation.projectTaskAllocation.dto.SkillDTO(" +
//    	       "s.skillId, s.skillName, s.skillDescription, s.category, s.level, s.createdAt) " +
//    	       "FROM UserSkill us JOIN FETCH us.skill s " +
//    	       "JOIN FETCH us.user u " +
//    	       "WHERE u.userId = :userId")
//    	List<SkillDTO> findSkillsByUserId(@Param("userId") Integer userId);
//    @Query("SELECT s.skillName FROM UserSkill us JOIN us.skill s WHERE us.user.userId = :userId")
//    List<String> findSkillNamesByUserId(@Param("userId") Integer userId);
//    @Query("SELECT s.skillName FROM UserSkill us JOIN us.skill s WHERE us.user.userId = :userId")
//    List<String> findSkillNamesByUserId(@Param("userId") Integer userId);
//
//    	List<SkillDTO> findSkillsByUserId(@Param("userId") Integer userId);
    @Query("SELECT new com.taskallocation.projectTaskAllocation.dto.SkillDTO(" +
    	       "s.skillId, s.skillName, s.skillDescription, s.category, s.level, s.createdAt) " +
    	       "FROM UserSkill us JOIN us.skill s " +
    	       "WHERE us.user.userId = :userId")
    	List<SkillDTO> findSkillsByUserId(@Param("userId") Integer userId);
}
