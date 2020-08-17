package com.techelevator.store;

import com.techelevator.Writer;

public class CashRegister {

	private int balance = 0;

	Writer fileWriter = new Writer();

	final int[] denominations = { 10000, 2000, 1000, 500, 100, 25, 10, 5, 1 };

	public void deposit(int amountToDeposit) {
		String printToFile = (String.format("ADD MONEY:              $%-7.2f  $%-7.2f", (double) amountToDeposit / 100,
				((double) getBalance() / 100) + (double) amountToDeposit / 100));
		balance = balance + amountToDeposit;
		fileWriter.writeFile(printToFile);

	}

	public void withdraw(int amountToWithdraw) {
		balance = balance - amountToWithdraw;
	}

	public void withdrawAndPrint(int amountToWithdraw, String printToFile) {
		balance = balance - amountToWithdraw;
		fileWriter.writeFile(printToFile);
	}

	public int[] getDenominations() {
		return denominations;
	}

	public int getBalance() {
		return balance;
	}

	public int[] getChange(int balance) {
		int afterTakingMoneyOut = 0;
		int[] count = breakdown(denominations, balance);
		String printToFile = (String.format("GIVE CHANGE:             $%-7.2f  $%-7.2f", (double) getBalance() / 100,
				(double) afterTakingMoneyOut));
		fileWriter.writeFile(printToFile);

		return count;

	}
	
	private static int[] breakdown(int[] denominations, int amount) {

		int[] count = new int[denominations.length];

		// Loop through each denomination (starting at largest)

		for (int i = 0; i < denominations.length; i++) {

			// Use one of that denomination until we need something smaller

			while (amount >= denominations[i]) {
				count[i]++;
				amount -= denominations[i];
			}
		}

		return count;
	}

}
