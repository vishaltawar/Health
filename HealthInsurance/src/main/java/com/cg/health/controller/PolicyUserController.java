package com.cg.health.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.health.entities.PolicyUser;
import com.cg.health.model.LoginUser;
import com.cg.health.service.PolicyUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/health")
public class PolicyUserController {
	@Autowired
	public PolicyUserService userService;
	
	
	@GetMapping("/policyUser/getall")
	public List<PolicyUser> getUsers(){
		return userService.getUsers() ;
	}
	@PostMapping("/policyUser/add")
	public PolicyUser addUser(@RequestBody PolicyUser user){
		return userService.addUser(user);
	}

	@GetMapping("/policyUser/get/{userId}")
	public ResponseEntity<Optional<PolicyUser>> getPolicyUserById(@PathVariable int userId){
		Optional<PolicyUser> policyUser1= userService.getPolicyUserById(userId);
		
		return new ResponseEntity<Optional<PolicyUser>>(policyUser1, HttpStatus.OK);
	}
	
	@PostMapping("/policyUser/userLogin")
	public ResponseEntity<PolicyUser> doLogin(@RequestBody LoginUser loginReq1) {

		PolicyUser policyUser = userService.userLogin(loginReq1.getUserName(), loginReq1.getPassword());

		ResponseEntity<PolicyUser> responseEntity = new ResponseEntity<>(policyUser, HttpStatus.OK);

		return responseEntity;
	}
	@PutMapping("/policyUser/update")
	public ResponseEntity<Object> updatedPolicyUser(@RequestBody PolicyUser policyUser1) {

		ResponseEntity<Object> responseEntity = null;
		PolicyUser updatedPolicyUser1 = userService.updatePolicyUser(policyUser1);
		responseEntity = new ResponseEntity<>(updatedPolicyUser1, HttpStatus.OK);
		return responseEntity;
	}
}

