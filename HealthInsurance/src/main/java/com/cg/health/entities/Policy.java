package com.cg.health.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Policies")
public class Policy {
	@Id

	private int policyId;
	private String policyName;
	private float policyCost;
	private int policyDurationInYear;

	public Policy() {
		super();

	}

	public Policy(int policyId, String policyName, float policyCost, int policyDurationInYear) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.policyCost = policyCost;
		this.policyDurationInYear = policyDurationInYear;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public float getPolicyCost() {
		return policyCost;
	}

	public void setPolicyCost(float policyCost) {
		this.policyCost = policyCost;
	}

	public int getPolicyDurationInYear() {
		return policyDurationInYear;
	}

	public void setPolicyDurationInYear(int policyDurationInYear) {
		this.policyDurationInYear = policyDurationInYear;
	}

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", policyCost=" + policyCost
				+ ", policyDurationInYear=" + policyDurationInYear + "]";
	}

}