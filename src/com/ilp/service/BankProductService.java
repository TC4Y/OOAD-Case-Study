package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.CurrentAccount;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;
import com.ilp.service.operations.BankOperationsService;

public class BankProductService {

	private static ArrayList<Product> products = new ArrayList<Product>();

	public static ArrayList<Product> getProducts() {
		return products;
	}

	public static void createProduct() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Product Code");
		String productCode = scanner.nextLine();

		System.out.println("Enter Product Name");
		String productName = scanner.nextLine();
		
		ArrayList<Service> selectedServices = new ArrayList<Service>();

		int choice;
		int servicesLength;
		
		do {
			System.out.println("Select from the following options");
			int optionIndex = 1;
			ArrayList<Service> services = BankOperationsService.getServices();
			servicesLength = services.size();
			
			for (Service service : services) {
				System.out.print(optionIndex + ". ");
				System.out.println(service.getServiceCode() + "       " + service.getServiceName() + "       " + service.getRate());
				optionIndex++;
			}
			System.out.println(optionIndex + ". Create a New Service");
			System.out.println((optionIndex + 1) + ". Stop Adding Services");
			
			choice = scanner.nextInt();
			
			if (choice < 1 || choice > servicesLength + 2) {
				System.out.println("Enter Correct Option");
				continue;
			}
			else if (choice == servicesLength + 1) {
				BankOperationsService.createService();
			}
			else if (choice < servicesLength + 1) {
				Service selectedService = services.get(choice-1);
				selectedServices.add(selectedService);
			}
			
		} while (choice != servicesLength + 2);
		
		createProduct(productCode, productName, selectedServices);
		
	}

	private static void createProduct(String productCode, String productName, ArrayList<Service> services) {
		boolean productExists = false;
		Product createdProduct = null;
		for(Product product : products) {
			if (product.getProductName().equals(productName)) {
				productExists = true;
			}
		}
		
		if (!productExists) {
			if(productName.equalsIgnoreCase("Savings Max Account")) {
				createdProduct = new SavingsMaxAccount(productCode, productName, services);
			}
			else if(productName.equalsIgnoreCase("Current Account")) {
				createdProduct = new CurrentAccount(productCode, productName, services);
			}
			else if(productName.equalsIgnoreCase("Loan Account")) {
				createdProduct = new LoanAccount(productCode, productName, services);
			}
			if (createdProduct != null) {
				products.add(createdProduct);
			}
			else {
				System.out.println("Such an Product Type does not existl");
				return;
			}
			
			System.out.println("Product Successfully Created and added to Product List\n");
		}
		else {
			System.out.println("Product already exists\n");
			return;
		}
	}

}
