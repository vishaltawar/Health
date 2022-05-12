package com.cg.health.controller;

import java.util.List;
import java.util.Optional;

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

import com.cg.health.entities.Admin;
import com.cg.health.model.LoginAdmin;
import com.cg.health.service.AdminService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/health")
public class AdminController {

	@Autowired
	public AdminService adminService;

	@GetMapping("/admins")
	public ResponseEntity<List<Admin>> getAdminList() {
		List<Admin> adminlist = adminService.getAdmin();

		return new ResponseEntity<List<Admin>>(adminlist, HttpStatus.OK);
	}

	@PostMapping("/admins/add")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin1) {
		Admin admin2 = adminService.addAdmin(admin1);

		return new ResponseEntity<Admin>(admin2, HttpStatus.OK);
	}

	@GetMapping("/admins/get/{adminId}")
	public ResponseEntity<Optional<Admin>> getAdminById(@PathVariable int adminId) {
		Optional<Admin> admin1 = adminService.getAdminById(adminId);

		return new ResponseEntity<Optional<Admin>>(admin1, HttpStatus.OK);
	}

	@PostMapping("/admins/login")
	public ResponseEntity<Admin> doLogin(@RequestBody LoginAdmin loginReq) {

		Admin admin = adminService.adminLogin(loginReq.getUserName(), loginReq.getPassword());

		ResponseEntity<Admin> responseEntity = new ResponseEntity<>(admin, HttpStatus.OK);

		return responseEntity;
	}

	@PutMapping("admins/update")
	public ResponseEntity<Object> updatedAdmin(@RequestBody Admin admin1) {

		ResponseEntity<Object> responseEntity = null;
		Admin updatedAdmin1 = adminService.updateAdmin(admin1);
		responseEntity = new ResponseEntity<>(updatedAdmin1, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("admins/delete/{adminId}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable("adminId") int adminId) {
		adminService.deleteAdminById(adminId);
		return new ResponseEntity<>("Admin is Deleted", HttpStatus.OK);
	}
}
