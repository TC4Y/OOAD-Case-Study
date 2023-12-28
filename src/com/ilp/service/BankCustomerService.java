package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;

public class BankCustomerService {
	
	private static ArrayList<Customer> customers = new ArrayList<Customer>();

	public static ArrayList<Customer> getCustomers() {
		return customers;
	}

	public static void createCustomer() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Customer Code");
		String customerCode = scanner.nextLine();
		
		System.out.println("Enter Customer Name");
		String customerName = scanner.nextLine();
		
		ArrayList<Account> createdAccounts = BankAccountService.createAccount();
		createCustomer(customerCode, customerName, createdAccounts);
		
	}
	
	private static void createCustomer(String customerCode, String customerName, ArrayList<Account> accounts) {
		boolean customerExists = false;
		Customer createdCustomer = null;
		
		for(Customer customer : customers) {
			if (customer.getCustomerCode().equals(customerCode)) {
				customerExists = true;
			}
		}
		
		if(!customerExists) {
			createdCustomer = new Customer(customerCode, customerName, accounts);
			customers.add(createdCustomer);
			System.out.println("Service Successfully Created and added to Service List\n");
		}
		else {
			System.out.println("Customer already exists\n");
			return;
		}
	}
	
	public static void displayCustomers() {
		for(Customer customer : customers) {
			displayCustomer(customer);
		}
	}
	
	public static void displayCustomer(Customer customer) {
		System.out.println("Customer Code       Customer Name");
		System.out.println(customer.getCustomerCode() + "       " + customer.getCustomerName());
		System.out.println(customer.getCustomerName() + " has the following accounts: ");
		BankAccountService.displayAccounts(customer.getAccounts());
	}

	public static Customer getOneCustomer(String customerCode) {
		Customer matchedCustomer = null;
		for(Customer customer : customers) {
			if (customer.getCustomerCode().equals(customerCode)) {
				matchedCustomer = customer;
			}
		}
		return matchedCustomer;
	}

}
