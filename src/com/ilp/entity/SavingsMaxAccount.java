package com.ilp.entity;

import java.util.ArrayList;

public class SavingsMaxAccount extends Product {

	private static double minimumBalance = 1000;
	
	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> services) {
		super(productCode, productName, services);
		
	}

	public static double getMinimumBalance() {
		return minimumBalance;
	}

//	public static void setMinimumBalance(double minimumBalance) {
//		minimumBalance = minimumBalance;
//	}

}
