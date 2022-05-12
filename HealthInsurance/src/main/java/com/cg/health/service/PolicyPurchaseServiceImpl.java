package com.cg.health.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.health.dao.PolicyDao;
import com.cg.health.dao.PolicyPurchaseDao;
import com.cg.health.dao.PolicyUserDao;
import com.cg.health.entities.Policy;
import com.cg.health.entities.PolicyPurchase;
import com.cg.health.entities.PolicyUser;
import com.cg.health.exception.PolicyNotFoundException;
import com.cg.health.exception.PolicyPurchaseAlreadyExistsException;
import com.cg.health.exception.PolicyPurchaseNotFoundException;
import com.cg.health.exception.PolicyUserNotFoundException;
import com.cg.health.model.PrimiumCalculator;

@Service
public class PolicyPurchaseServiceImpl implements PolicyPurchaseService {

	@Autowired
	private PolicyPurchaseDao policyPurchaseDao;
	@Autowired
	private PolicyDao policyDao;
	@Autowired
	private PolicyUserDao userDao;

	@Override
	public PolicyPurchase addPolicyPurchase(int policyId, int userId, int policyPurchaseDuration)
			throws PolicyPurchaseAlreadyExistsException {
		Optional<Policy> optionalPolicy = policyDao.findById(policyId);
		Optional<PolicyUser> optionalPolicyUser = userDao.findById(userId);
		if (optionalPolicy.isEmpty()) {
			throw new PolicyNotFoundException();
		}

		if (optionalPolicyUser.isEmpty()) {
			throw new PolicyUserNotFoundException();
		}
		Policy policy = optionalPolicy.get();
		PolicyUser policyUser = optionalPolicyUser.get();

		PolicyPurchase policyPurchase = new PolicyPurchase();
		policyPurchase.setPurchaseDate(LocalDate.now());

		policyPurchase.setPolicyPuchaseDuration(policyPurchaseDuration);
		policyPurchase.setPurchaseEndDate(LocalDate.now().plusYears(policyPurchase.getPolicyPuchaseDuration()));

		policyPurchase.setPolicy(policy);
		policyPurchase.setPolicyUser(policyUser);
		int age = LocalDate.now().getYear() - policyUser.getDob().getYear();
		int payingTerm = policyPurchase.getPolicyPuchaseDuration();
		double sumAssured = policy.getPolicyCost();

//		 int policyTerm = policy.getPolicyDurationInYear();
		PrimiumCalculator primiumcalculator = new PrimiumCalculator(age, payingTerm, sumAssured);
		double primiumAmount = calculatePrimium(primiumcalculator);
		policyPurchase.setPremiumAmount(primiumAmount);
		PolicyPurchase savedPolicyPurchase = policyPurchaseDao.save(policyPurchase);
		return savedPolicyPurchase;

	}

	@Override
	public Optional<PolicyPurchase> getPolicyPurchaseUserById(long policyPurchaseUserId)
			throws PolicyPurchaseNotFoundException {

		PolicyPurchase policyPurchase;
		if (policyPurchaseDao.findById(policyPurchaseUserId).isEmpty()) {
			throw new PolicyPurchaseNotFoundException();
		} else {
			policyPurchase = policyPurchaseDao.findById(policyPurchaseUserId).get();
		}
		return Optional.of(policyPurchase);
	}

	@Override
	public double calculatePrimium(PrimiumCalculator primiumCalculator) {
		double primium = 0.2f;
		if (primiumCalculator.getAge() <= 18) {
			primium = (primiumCalculator.getSumAssured() / (primiumCalculator.getPayingTerm() * 12)) * 1.1;
		}

		else if (primiumCalculator.getAge() <= 30) {
			primium = (primiumCalculator.getSumAssured() / (primiumCalculator.getPayingTerm() * 12)) * 1.2;

		} else if (primiumCalculator.getAge() <= 50) {
			primium = (primiumCalculator.getSumAssured() / (primiumCalculator.getPayingTerm() * 12)) * 1.4;

		} else if (primiumCalculator.getAge() <= 100) {
			primium = (primiumCalculator.getSumAssured() / (primiumCalculator.getPayingTerm() * 12)) * 1.6;
		}

		return primium;
	}

	@Override
	public List<PolicyPurchase> getPolicyPurchase() throws PolicyPurchaseNotFoundException {

		return policyPurchaseDao.findAll();
	}

	@Override
	public String deletePolicyPurchaseById(int policyPurchaseId) {

		policyPurchaseDao.deleteById((long) policyPurchaseId);

		return " Policy Purchase Deleted";
	}

	@Override
	public PolicyPurchase updatePolicyPurchase(PolicyPurchase policyPurchase) {

		Policy policy = policyPurchase.getPolicy();
		PolicyUser policyUser = policyPurchase.getPolicyUser();

		policyPurchase.setPurchaseDate(LocalDate.now());

		policyPurchase.setPurchaseEndDate(LocalDate.now().plusYears(policyPurchase.getPolicyPuchaseDuration()));

		int age = LocalDate.now().getYear() - policyUser.getDob().getYear();
		int payingTerm = policyPurchase.getPolicyPuchaseDuration();
		double sumAssured = policy.getPolicyCost();

		PrimiumCalculator primiumcalculator = new PrimiumCalculator(age, payingTerm, sumAssured);
		double primiumAmount = calculatePrimium(primiumcalculator);
		policyPurchase.setPremiumAmount(primiumAmount);

		return policyPurchaseDao.save(policyPurchase);
	}

}
