package com.techelevator.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.techelevator.store.CartItem;
import com.techelevator.store.CashRegister;
import com.techelevator.store.StoreItem;

public class Menu {

	private CartItem cartItem;
	private CashRegister cashRegister;
	private StoreItem storeItem;
	private List<String[]> inventoryFileArrays;

	private boolean keepGoing = true;
	int total = 0;

	Scanner input = new Scanner(System.in);

	public Menu() {
		storeItem = new StoreItem();
		cashRegister = new CashRegister();
		cartItem = new CartItem();
		storeItem.getNameMap();
		storeItem.getPriceMap();
		storeItem.getFoodTypeMap();
		inventoryFileArrays = storeItem.getInventoryFileArrays();
	}

	public void getUserInput() {

		System.out.println("(1) Display Catering Items");
		System.out.println("(2) Order");
		System.out.println("(3) Quit");
		System.out.println("---------------------------");
		String userInput = input.nextLine();

		if (userInput.equals("1")) {
			displayCateringItems(inventoryFileArrays);
		} else if (userInput.equals("2")) {
			order();
		} else if (userInput.equals("3")) {
			quit();
		} else {
			System.out.println(userInput + " is an invalid user input.");
			System.out.println();
		}
	}

	public void displayCateringItems(List<String[]> inventoryFileArrays) {
		System.out.println("");
		
		System.out.println("Code  Item                   Price");
		System.out.println("*--------------------------------*");
		
		for (String[] array : inventoryFileArrays) {
			String stringLength20 = String.format("%-23s", array[1]);	
			System.out.printf(array[0] + "    " + stringLength20 + "$" +  array[2]);
			System.out.println();

		}
		System.out.println();
	}

	public void order() {
		String userInput = "";
		while (!userInput.equals("3")) {
			System.out.println();
			System.out.println("(1) Add Money");
			System.out.println("(2) Select Products");
			System.out.println("(3) Complete Transaction");
			System.out.printf("Current Account Balance:  $%,-7.2f", +(double) cashRegister.getBalance() / 100);
			System.out.println();
			System.out.println("----------------------------------");
			try {
				userInput = input.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry.");
				
			}

			if (userInput.equals("1")) {
				addMoney();
				
			} else if (userInput.equals("2")) {
				selectProducts();
			} else if (!userInput.equals("3")) {
				System.out.println("Invalid entry.");
			}

		}

		completeTransaction();
	}

	public void quit() {
		System.out.println("Goodbye!");
		keepGoing = false;
	}

	public void addMoney() {

		int amountToAdd = 0;
		boolean validAmount = false;
		while (!validAmount) {
			System.out.println("How much money would you like to add?");

			try {
				amountToAdd = input.nextInt();
				input.nextLine();

			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Please enter a valid, whole dollar amount");
				System.out.println();
				continue;
			}
			amountToAdd *= 100;
			if (amountToAdd + cashRegister.getBalance() <= 500000 && amountToAdd >= 0) {
				
				validAmount = true;
			} else {
				System.out.printf("Current balance is $%,-7.2f", +(double) cashRegister.getBalance() / 100);
				System.out.println();
				double spaceInRegister = 5000 - (cashRegister.getBalance() / 100);
				System.out.printf("You can only enter amounts up to $%,-7.2f", + spaceInRegister);
				System.out.println();

			}
		}
		System.out.println("You added $" + amountToAdd / 100 + ".00" );
		cashRegister.deposit(amountToAdd);

	}

	public void selectProducts() {
		boolean validProduct = false;
		String userProductChoice = "";
		while (!validProduct) {
			System.out.println();
			System.out.println("Enter the product code: ");
			userProductChoice = input.nextLine().toUpperCase();
			if (storeItem.getName(userProductChoice) == null) {
				System.out.println("Invalid Product Code");
				continue;
			}
			
			System.out.println("You selected " + storeItem.getName(userProductChoice));
			validProduct = true;
		}
		selectQuantity(userProductChoice);
	}

	public void selectQuantity(String userProductChoice) {
		
		int amountInStore = storeItem.getAmountOfItems(userProductChoice);
		boolean validQuantity = false;
		int userQuantity = 0;
		while (!validQuantity) {
			try {
				System.out.println("How many would you like?");
				userQuantity = input.nextInt();
				input.nextLine();
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("Invalid entry!");
				continue;
			}
			if (userQuantity <= 0) {
				System.out.println("Please enter a valid quantity.");
				validQuantity = false;
				continue;
			}
			if (userQuantity > amountInStore) {
				if (amountInStore == 0) {
					System.out.println(storeItem.getName(userProductChoice) + " is out of stock!");
					System.out.println();
					return;
				} else {
					System.out.println("There are only " + storeItem.getAmountOfItems(userProductChoice) + " "
							+ storeItem.getName(userProductChoice) + "(s) left.");
					continue;
				}
			}
			validQuantity = true;
		}
		buyItems(userProductChoice, userQuantity);
	}

	public void buyItems(String userProductChoice, int userQuantity) {
		if (cashRegister.getBalance() >= storeItem.getPrice(userProductChoice) * userQuantity) {
			System.out.println("You bought " + userQuantity + " " + storeItem.getName(userProductChoice) + "(s)!");
			storeItem.sellItem(userProductChoice, userQuantity);
			cartItem.addToCart(userProductChoice, userQuantity);
			String amountAndTypeInCart = new String(Integer.toString(cartItem.getAmountOfItems(userProductChoice))
					+ " " + storeItem.getName(userProductChoice) + " " + userProductChoice);
			double afterWithdraw = ((double) cashRegister.getBalance() / 100)
					- ((double) storeItem.getPrice(userProductChoice) * userQuantity / 100);

			String printToFile = (String.format(amountAndTypeInCart + " $%-7.2f  $%-7.2f",
					(double) storeItem.getPrice(userProductChoice) * userQuantity / 100, afterWithdraw));
			cashRegister.withdrawAndPrint(storeItem.getPrice(userProductChoice) * userQuantity, printToFile);
			

		} else {
			System.out.println("Insufficient Funds!");
			System.out.println();
		}
	}

	public void completeTransaction() {

		System.out.println("Purchase Receipt");
		System.out.println();
		for (String code : cartItem.getCartMap().keySet()) {
			int amountInCart = cartItem.getCartMap().get(code);
			int totalPriceOfItems = storeItem.getPrice(code) * amountInCart;
			if (amountInCart > 0) {
				System.out.printf("%-4s", amountInCart);
				System.out.printf("%-12s", storeItem.getFoodType(code));
				System.out.printf("%-18s", storeItem.getName(code));
				System.out.printf("$%,-10.2f", (double) storeItem.getPrice(code) / 100);
				System.out.printf("$%,-10.2f", (double) totalPriceOfItems / 100);
				System.out.println();
				total += totalPriceOfItems;
				System.out.println();
			} else {
				continue;
			}
			
		}
		System.out.println("Your total is $" + (double) total / 100);
		System.out.println();

		int[] change = cashRegister.getChange(cashRegister.getBalance());
		System.out.printf("Your Change is $%,-6.2f", +(double) cashRegister.getBalance() / 100);
		System.out.println();
		for (int i = 0; i < cashRegister.getDenominations().length; i++) {
			if (change[i] > 0) {

				System.out.printf(change[i] + " x $%,-7.2f", +(double) cashRegister.getDenominations()[i] / 100);
				System.out.println();

			}

		}
		cartItem.setStartingInventoryMap();
		total = 0;
		cashRegister.withdraw(cashRegister.getBalance());
		getUserInput();

	}

	public boolean getKeepGoing() {
		return keepGoing;
	}

}
