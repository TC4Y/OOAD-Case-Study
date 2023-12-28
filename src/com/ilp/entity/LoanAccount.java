package com.ilp.entity;

import java.util.ArrayList;

public class LoanAccount extends Product {

	private double chequeDepositRate = 0.3;
	
	public LoanAccount(String productCode, String productName, ArrayList<Service> services) {
		super(productCode, productName, services);
	}

	public double getChequeDepositRate() {
		return chequeDepositRate;
	}

	public void setChequeDepositRate(double chequeDepositRate) {
		this.chequeDepositRate = chequeDepositRate;
	}

}
