package com.ilp.service.operations;

import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.SavingsMaxAccount;

public class AccountOperationsService {

	public static void depositMoney(Account account) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Amount to be deposited: ");
		double amountToDeposit = scanner.nextDouble();

		if (amountToDeposit < 0) {
			System.out.println("Enter Valid Amount !!!");
			return;
		}

		account.setBalance(account.getBalance() + amountToDeposit);

		System.out.println("Your Current Balance: " + account.getBalance());
	}

	public static void withdrawMoney(Account customerSelectedAccount) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Amount to be Withdrawn: ");
		double amountToWithdraw = scanner.nextDouble();

		if (customerSelectedAccount.getProduct() instanceof SavingsMaxAccount) {
			if (customerSelectedAccount.getBalance() - amountToWithdraw < SavingsMaxAccount.getMinimumBalance()) {
				System.out.println("Sorry!!!!!!!!!!!!! A mininmum balance of " + SavingsMaxAccount.getMinimumBalance()
						+ " should be mainted in the account.");
				return;
			}
			customerSelectedAccount.setBalance(customerSelectedAccount.getBalance() - amountToWithdraw);
		} else {
			if (customerSelectedAccount.getBalance() - amountToWithdraw < 0) {
				System.out.println("Sorry!!!!!!!!!!!!! You don't have enough balance");
				return;
			}
			customerSelectedAccount.setBalance(customerSelectedAccount.getBalance() - amountToWithdraw);
		}
		
		System.out.println("Your Current Balance: " + customerSelectedAccount.getBalance());
	}

}
