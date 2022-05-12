package com.cg.health.model;

public class PrimiumCalculator {

	private int age;
	private int payingTerm;
	private double sumAssured;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public PrimiumCalculator(int age, int payingTerm, double sumAssured) {
		super();
		this.age = age;
		this.payingTerm = payingTerm;
		this.sumAssured = sumAssured;

	}

	public int getPayingTerm() {
		return payingTerm;
	}

	public void setPayingTerm(int payingTerm) {
		this.payingTerm = payingTerm;
	}

	public double getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(double sumAssured) {
		this.sumAssured = sumAssured;
	}
}
