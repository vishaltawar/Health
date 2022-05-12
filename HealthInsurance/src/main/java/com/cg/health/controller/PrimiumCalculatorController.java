package com.cg.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.health.model.PrimiumCalculator;
import com.cg.health.service.PolicyPurchaseService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/health")
public class PrimiumCalculatorController {

	@Autowired
	public PolicyPurchaseService policyPurchaseService;

	@PostMapping("/primiumCalculator")
	public double calculatePrimium(@RequestBody PrimiumCalculator primiumCalculator) {

		return policyPurchaseService.calculatePrimium(primiumCalculator);

	}
}
