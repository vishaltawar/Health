package com.cg.health.service;

import java.util.List;
import java.util.Optional;

import com.cg.health.entities.PolicyPurchase;
import com.cg.health.exception.PolicyPurchaseAlreadyExistsException;
import com.cg.health.exception.PolicyPurchaseNotFoundException;
import com.cg.health.model.PrimiumCalculator;

public interface PolicyPurchaseService {

	public List<PolicyPurchase> getPolicyPurchase() throws PolicyPurchaseNotFoundException;

	public PolicyPurchase addPolicyPurchase(int policyId, int userId, int policyPurchaseDuration)
			throws PolicyPurchaseAlreadyExistsException;

	public Optional<PolicyPurchase> getPolicyPurchaseUserById(long policyPurchaseUserId)
			throws PolicyPurchaseNotFoundException;

	

	public String deletePolicyPurchaseById(int policyPurchaseId);

	public PolicyPurchase updatePolicyPurchase(PolicyPurchase policyPurchase1);

	public double calculatePrimium(PrimiumCalculator primiumCalculator);

}
