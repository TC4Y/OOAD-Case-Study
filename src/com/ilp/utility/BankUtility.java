package com.ilp.utility;

import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.service.BankAccountService;
import com.ilp.service.BankCustomerService;
import com.ilp.service.BankProductService;
import com.ilp.service.operations.AccountOperationsService;
import com.ilp.service.operations.BankOperationsService;

public class BankUtility {

	public static void main(String[] args) {
		showMainMenu();
	}

	private static void showMainMenu() {

//		ArrayList<Service> serviceList = new ArrayList<Service>();
		Scanner scanner = new Scanner(System.in);
		int choice;
		System.out.println("**********Welcome to Bank**********\n");
		do {
			System.out.println("Select one of the options below: ");
			System.out.println("1. Create Service \n" + "2. Create Product \n" + "3. Create Customer \n"
					+ "4. Manage Accounts \n" + "5. Display Customer \n" + "6. Exit \n");
			System.out.println("Enter you choice :");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				BankOperationsService.createService();
				break;
			case 2:
				BankProductService.createProduct();
				break;
			case 3:
				BankCustomerService.createCustomer();
				break;
			case 4:
//			For Managing Accounts
				scanner.nextLine();
				System.out.println("Enter Customer Code");
				String customerCode = scanner.nextLine();
				
				Customer customer = BankCustomerService.getOneCustomer(customerCode);
				if(customer == null) {
					System.out.println("No Customer with the entered Code Exists !!!");
					break;
				}
				
				System.out.println(customer.getCustomerName() + " has the following accounts. Select One: ");
				BankAccountService.displayAccounts(customer.getAccounts());
				int accountChoice = scanner.nextInt();
				Account customerSelectedAccount = customer.getAccounts().get(accountChoice-1);
				
				do {
				System.out.println("Select from the options: ");
				System.out.println("1. Deposit \n"
						+ "2. Withdraw \n"
						+ "3. Display Balance \n");
				int operationChoice = scanner.nextInt();
				
				
				switch (operationChoice) {
				case 1:
					AccountOperationsService.depositMoney(customerSelectedAccount);
					break;
				case 2:
					AccountOperationsService.withdrawMoney(customerSelectedAccount);
					break;
				case 3:
					BankCustomerService.displayCustomer(customer);
					break;
				default: 
					System.out.println("Enter Correct Option");
				}
				System.out.println("Do you want to continue (y/n)");
				} while(scanner.next().equalsIgnoreCase("y"));

				break;
			case 5:
				BankCustomerService.displayCustomers();
				break;
			case 6:
				System.out.println("Thank you for using Bank");
				return;
			default:
				System.out.println("Enter a valid option !!!");
			}
		} while (choice != 6);

	}

}
