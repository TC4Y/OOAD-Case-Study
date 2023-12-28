package com.ilp.service.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.ilp.entity.Service;

public class BankOperationsService {
	
	private static ArrayList<Service> services = new ArrayList<Service>();
	
	public static ArrayList<Service> getServices() {
		return services;
	}
	
	public static Service createService() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Service Code: ");
		String serviceCode = scanner.nextLine();
		
		System.out.println("Enter Service Name: ");
		String serviceName = scanner.nextLine();
		
		System.out.println("Enter Service Rate: ");
		double serviceRate = scanner.nextDouble();
		
		return createService(serviceCode, serviceName, serviceRate);
		
	}
	
	private static Service createService(String serviceCode, String serviceName, double serviceRate) {
		boolean serviceExists = false;
		Service createdService = null;
		for(Service service : services) {
			if (service.getServiceName().equals(serviceName)) {
				serviceExists = true;
			}
		}
		
		if (!serviceExists) {
			createdService = new Service(serviceCode, serviceName, serviceRate);
			services.add(createdService);
			System.out.println("Service Successfully Created and added to Service List\n");
		}
		else {
			System.out.println("Service already exists\n");
		}
		return createdService;
	}
	
	

}
