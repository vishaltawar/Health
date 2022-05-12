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

import com.cg.health.entities.Policy;
import com.cg.health.service.PolicyServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/health")
public class PolicyController {
	@Autowired
	public PolicyServiceImpl policyService;

	@GetMapping("/policies/list")
	public ResponseEntity<List<Policy>> getPolicyList() throws Exception {
		List<Policy> policylist = policyService.getPolicies();

		return new ResponseEntity<List<Policy>>(policylist, HttpStatus.OK);
	}

	@PostMapping("/policies/add")
	public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy1) {
		Policy policy2 = policyService.addPolicies(policy1);

		return new ResponseEntity<Policy>(policy2, HttpStatus.OK);
	}

	@GetMapping("/policies/get/{policyId}")
	public ResponseEntity<Optional<Policy>> getPolicyById(@PathVariable int policyId) {
		Optional<Policy> policy1 = policyService.getPolicyById(policyId);

		return new ResponseEntity<Optional<Policy>>(policy1, HttpStatus.OK);
	}

	@DeleteMapping("/policies/delete/{policyId}")
	public ResponseEntity<Object> deletePolicy(@PathVariable("policyId") int policyId) {
		policyService.deletePolicyById(policyId);
		return new ResponseEntity<>("Policy is Deleted", HttpStatus.OK);
	}

	@PutMapping("/policies/update")
	public ResponseEntity<Object> updatedPolicy(@RequestBody Policy policy1) {

		ResponseEntity<Object> responseEntity = null;
		Policy updatedPolicy1 = policyService.updatePolicy(policy1);
		responseEntity = new ResponseEntity<>(updatedPolicy1, HttpStatus.OK);
		return responseEntity;
	}
}
