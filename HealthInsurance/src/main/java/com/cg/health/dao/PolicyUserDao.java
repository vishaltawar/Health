package com.cg.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.health.entities.PolicyUser;

@Repository
public interface PolicyUserDao extends JpaRepository<PolicyUser, Integer> {
	
	@Query("Select u from PolicyUser u where u.userName = :uname and u.password = :upwd")
	public PolicyUser doUserLogin(@Param("uname") String userName, @Param("upwd") String password);
	

}
