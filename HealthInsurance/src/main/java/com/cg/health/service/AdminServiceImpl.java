package com.cg.health.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.health.dao.AdminDao;
import com.cg.health.entities.Admin;
import com.cg.health.exception.AdminAlreadyExistsException;
import com.cg.health.exception.AdminNotFoundException;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public List<Admin> getAdmin() throws AdminNotFoundException {

		return adminDao.findAll();
	}

	@Override
	public Admin addAdmin(Admin admin) throws AdminAlreadyExistsException {
		if (adminDao.existsById(admin.getAdminId())) {
			throw new AdminAlreadyExistsException();
		}
		Admin savedAdmin = adminDao.save(admin);
		return savedAdmin;
	}

	@Override
	public Optional<Admin> getAdminById(int adminId) throws AdminNotFoundException {
		Admin admin;
		if (adminDao.findById(adminId).isEmpty()) {
			throw new AdminNotFoundException();
		} else {
			admin = adminDao.findById(adminId).get();
		}
		return Optional.of(admin);
	}

	@Override
	public Admin adminLogin(String userName, String password) {

		Admin admin = adminDao.doAdminLogin(userName, password);

		if (admin == null) {
			throw new AdminNotFoundException("Username or password is incorrect");
		}

		return admin;
	}
	
	@Override
	public Admin updateAdmin(Admin admin) {

	return adminDao.save(admin);
	}

	@Override
	public String deleteAdminById(int adminId) {
		
		
		adminDao.deleteById(adminId);
	    
		  return " Admin Deleted";
	}
	
}
