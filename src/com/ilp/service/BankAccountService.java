package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;
import com.ilp.service.operations.BankOperationsService;

public class BankAccountService {

	public static ArrayList<Account> createAccount() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Account Number");
		String accountNo = scanner.nextLine();
		
//		Customer can have multiple savings account, for now
		ArrayList<Account> selectedAccounts = new ArrayList<Account>();

		int choice;
		int productsLength;
		
		do {
			System.out.println("Select from the following options");
			int optionIndex = 1;
			ArrayList<Product> products = BankProductService.getProducts();
			productsLength = products.size();
			for (Product product : products) {
				System.out.print(optionIndex + ". ");
				System.out.println(product.getProductCode() + "       " + product.getProductName());
				optionIndex++;
			}
			System.out.println(optionIndex + ". Create a New Product");
			System.out.println((optionIndex + 1) + ". Stop Adding Products");
			
			choice = scanner.nextInt();
			
			if (choice < 1 || choice > productsLength + 2) {
				System.out.println("Enter Correct Option");
				continue;
			}
			else if (choice == productsLength + 1) {
				BankProductService.createProduct();
			}
			else if (choice < productsLength + 1) {
				Product selectedProduct = products.get(choice-1);
				Account createdAccount = createAccount(accountNo, selectedProduct.getProductName(), selectedProduct);
				selectedAccounts.add(createdAccount);
			}
			
		} while (choice != productsLength + 2);
		
		return selectedAccounts;
	}
	
	private static Account createAccount(String accountNumber, String accountName, Product product) {
		Account createdAccount;
		if (product.getProductName().equals("Savings Max Account")) {
			double minimumBalance = SavingsMaxAccount.getMinimumBalance();
			createdAccount = new Account(accountNumber, accountName, minimumBalance, product);
		}
		else {
			createdAccount = new Account(accountNumber, accountName, 0, product);
		}
		return createdAccount;
	}
	
	public static void displayAccounts(ArrayList<Account> accounts) {
		int indexOfAccount = 1;
		for(Account account : accounts) {
			System.out.print(indexOfAccount + ". ");
			displayAccount(account);
		}
	}

	public static void displayAccount(Account account) {
		System.out.println(account.getAccountType());
		System.out.println("Account Number       Account Type       Balance");
		System.out.println(account.getAccountNo() + "       " + account.getAccountType() + "       " + account.getBalance());
	}

}
