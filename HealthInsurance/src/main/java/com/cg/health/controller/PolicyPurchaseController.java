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

import com.cg.health.entities.PolicyPurchase;
import com.cg.health.model.PolicyPurchaseReq;
import com.cg.health.service.PolicyPurchaseService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/health")
public class PolicyPurchaseController {
	@Autowired
	public PolicyPurchaseService policyPurchaseService;

	@GetMapping("/policyPurchase")
	public ResponseEntity<List<PolicyPurchase>> getPolicyPurchaseList() {
		List<PolicyPurchase> policyPurchaselist = policyPurchaseService.getPolicyPurchase();

		return new ResponseEntity<List<PolicyPurchase>>(policyPurchaselist, HttpStatus.OK);
	}

	@PostMapping("/policyPurchase/add")
	public ResponseEntity<PolicyPurchase> addPolicyPurchase(@RequestBody PolicyPurchaseReq policyPurchaseReq) {
		PolicyPurchase policyPurchase= policyPurchaseService.addPolicyPurchase(policyPurchaseReq.getPolicyId(),policyPurchaseReq.getUserId(), policyPurchaseReq.getPolicyPurchaseDuration());
		
		return new ResponseEntity<>(policyPurchase, HttpStatus.OK);
	}

	@GetMapping("/policyPurchase/get/{id}")
	public ResponseEntity<Optional<PolicyPurchase>> getPolicyPurchaseById(@PathVariable int id) {
		Optional<PolicyPurchase> policyPurchase1 = policyPurchaseService.getPolicyPurchaseUserById(id);
		
		return new ResponseEntity<Optional<PolicyPurchase>>(policyPurchase1, HttpStatus.OK);
	}
	
	@DeleteMapping("/policyPurchase/delete/{policyPurchaseId}")
	public ResponseEntity<Object> deletePolicyPurchase(@PathVariable("policyPurchaseId") int policyPurchaseId) {
		policyPurchaseService.deletePolicyPurchaseById(policyPurchaseId);
		return new ResponseEntity<>("Policy Purchase is Deleted", HttpStatus.OK);
	}


	@PutMapping("/policyPurchase/update")
	public ResponseEntity<Object> updatedPolicyPurchase(@RequestBody PolicyPurchase policyPurchase1) {

		ResponseEntity<Object> responseEntity = null;
		PolicyPurchase updatedPolicyPurchase1 = policyPurchaseService.updatePolicyPurchase(policyPurchase1);
		responseEntity = new ResponseEntity<>(updatedPolicyPurchase1, HttpStatus.OK);
		return responseEntity;
	}
	

}
