package com.cg.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.health.entities.Admin;

@Repository
public  interface AdminDao extends JpaRepository<Admin, Integer>{
	
	@Query("Select a from Admin a where a.userName = :uname and a.password = :upwd")
	public Admin doAdminLogin(@Param("uname") String userName, @Param("upwd") String password);
	
}
