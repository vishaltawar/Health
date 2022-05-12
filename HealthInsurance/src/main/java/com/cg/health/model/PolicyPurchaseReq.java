package com.cg.health.model;

public class PolicyPurchaseReq {
	
	private int policyId;
	private int userId;
	private int policyPurchaseDuration;
	
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPolicyPurchaseDuration() {
		return policyPurchaseDuration;
	}
	public void setPolicyPurchaseDuration(int policyPurchaseDuration) {
		this.policyPurchaseDuration = policyPurchaseDuration;
	}

}
