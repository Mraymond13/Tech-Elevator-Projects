package com.techelevator.tenmo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.tenmo.models.Accounts;
import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.AuthenticationServiceException;
import com.techelevator.tenmo.services.TransferService;
import com.techelevator.tenmo.services.UserService;
import com.techelevator.view.ConsoleService;

public class App {

	private static final String API_BASE_URL = "http://localhost:8080/";

	private static final String MENU_OPTION_EXIT = "Exit";
	private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
	private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
	private static final String[] LOGIN_MENU_OPTIONS = { LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN,
			MENU_OPTION_EXIT };
	private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
	private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
	private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
	private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS,
			MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS,
			MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };

	private Principal principal;
	private Transfer transfer;
	private AuthenticatedUser currentUser;
	private ConsoleService console;
	private AuthenticationService authenticationService;
	private AccountService accountService;
	private UserService userService;
	private TransferService transferService;

	public static void main(String[] args) {
		App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL));
		app.run();
	}

	public App(ConsoleService console, AuthenticationService authenticationService) {
		this.console = console;
		this.authenticationService = authenticationService;
	}

	public void run() {
		System.out.println("*********************");
		System.out.println("* Welcome to TEnmo! *");
		System.out.println("*********************");

		registerAndLogin();

		accountService = new AccountService(currentUser.getToken(), API_BASE_URL);
		userService = new UserService(currentUser.getToken(), API_BASE_URL);
		transferService = new TransferService(currentUser.getToken(), API_BASE_URL);
		mainMenu();
	}

	private void mainMenu() {
		while (true) {
			String choice = (String) console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
				viewCurrentBalance();
			} else if (MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
				viewTransferHistory();
			} else if (MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
				viewPendingRequests();
			} else if (MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
				sendBucks();
			} else if (MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
				requestBucks();
			} else if (MAIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else {
				// the only other option on the main menu is to exit
				exitProgram();
			}
		}
	}

	private void viewCurrentBalance() {

		Accounts accounts = accountService.showBalance();

		String message = String.format("Balance $%6.2f", accounts.getBalance());

		console.displayMessage(message);

	}

	private void viewTransferHistory() {

		Accounts accounts = accountService.showBalance();
		List<Transfer> transferHistory = new ArrayList<Transfer>();
		transferHistory = (transferService.getTransferHistory(accounts.getAccountId()));
		console.displayMessage("---------------------------------");
		for (Transfer transfer : transferHistory) {
			String moneyToTransferFormatted = String.format(" $%6.2f", transfer.getMoneyToTransfer());
			if (transfer.getUserIdTransferFrom() == accounts.getAccountId()) {
				console.displayMessage("Id: " + transfer.getTransferId() + ".) You sent " + moneyToTransferFormatted
						+ " to " + transfer.getTransferUsername());
			} else {

				console.displayMessage("Id: " + transfer.getTransferId() + ".) You received " + moneyToTransferFormatted
						+ " from a friend!");
			}

		}
		console.displayMessage("---------------------------------");
		String transferDetailPrompt = "Please enter transfer ID to view details (0 to cancel)";
		String userInput = console.getUserInput(transferDetailPrompt);
		int userInputAsInt = Integer.parseInt(userInput);

		if (userInputAsInt == 0) {
			mainMenu();
		} else {
			Transfer transferToDisplay = transferService.getTransferDetails(userInputAsInt);
			if (transferToDisplay.getUserIdTransferTo() == accounts.getAccountId()) {
				console.displayMessage("Id: " + String.valueOf(transferToDisplay.getTransferId()));
				console.displayMessage("From: A friend");
				console.displayMessage("To: You");
				if (transferToDisplay.getTransferTypeId() == 1) {
					transferToDisplay.setTransferTypeDescription("Request");
					console.displayMessage("Type: " + transferToDisplay.getTransferTypeDescription());
				} else {
					transferToDisplay.setTransferTypeDescription("Send");
					console.displayMessage("Type: " + transferToDisplay.getTransferTypeDescription());
				}
				if (transferToDisplay.getTransferStatusId() == 1) {
					transferToDisplay.setTransferStatusDescription("Pending");
					console.displayMessage("Status: " + transferToDisplay.getTransferStatusDescription());
				} else if (transferToDisplay.getTransferStatusId() == 2) {
					transferToDisplay.setTransferStatusDescription("Approved");
					console.displayMessage("Status: " + transferToDisplay.getTransferStatusDescription());
				} else {
					transferToDisplay.setTransferStatusDescription("Rejected");
					console.displayMessage("Status: " + transferToDisplay.getTransferStatusDescription());
				}
				console.displayMessage("Amount " + String.valueOf(transferToDisplay.getMoneyToTransfer()));

			} else {
				console.displayMessage("Id: " + String.valueOf(transferToDisplay.getTransferId()));
				console.displayMessage("From: You");
				console.displayMessage("To: " + transferToDisplay.getTransferUsername());
				if (transferToDisplay.getTransferTypeId() == 1) {
					transferToDisplay.setTransferTypeDescription("Request");
					console.displayMessage("Type: " + transferToDisplay.getTransferTypeDescription());
				} else {
					transferToDisplay.setTransferTypeDescription("Send");
					console.displayMessage("Type: " + transferToDisplay.getTransferTypeDescription());
				}
				if (transferToDisplay.getTransferStatusId() == 1) {
					transferToDisplay.setTransferStatusDescription("Pending");
					console.displayMessage("Status: " + transferToDisplay.getTransferStatusDescription());
				} else if (transferToDisplay.getTransferStatusId() == 2) {
					transferToDisplay.setTransferStatusDescription("Approved");
					console.displayMessage("Status: " + transferToDisplay.getTransferStatusDescription());
				} else {
					transferToDisplay.setTransferStatusDescription("Rejected");
					console.displayMessage("Status: " + transferToDisplay.getTransferStatusDescription());
				}
				console.displayMessage("Amount " + String.valueOf(transferToDisplay.getMoneyToTransfer()));
			}
		}

	}

	private void viewPendingRequests() {
		
		Transfer transferOne = new Transfer();
		Transfer transfer2 = new Transfer();

		Accounts accounts = accountService.showBalance();
		List<Transfer> transferHistory = new ArrayList<Transfer>();
		transferHistory = (transferService.getTransferHistory(accounts.getAccountId()));
		console.displayMessage("---------------------------------");

		for (Transfer transfer : transferHistory) {
			if (transfer.getTransferStatusId() == 1 && transfer.getTransferTypeId() == 1
					&& accounts.getAccountId() != transfer.getUserIdTransferFrom()) {
				String moneyToTransferFormatted = String.format(" $%6.2f", transfer.getMoneyToTransfer());
				console.displayMessage(
						"Id: " + transfer.getTransferId() + ".) " + "A friend"
								+ " has requested " + moneyToTransferFormatted + " from You.");
				transferOne.setMoneyToTransfer(transfer.getMoneyToTransfer());
				transfer2.setMoneyToTransfer(-transfer.getMoneyToTransfer());
				
			}
		}
		console.displayMessage("---------------------------------");
		int userRequestChoice = console.getUserInputInteger("Which request would you like to manage?");
		int userStatusChoice = console.getUserInputInteger("(1) Return (2) Approve (3) Reject");
		if (userStatusChoice == 2) {
			Transfer transferTest = new Transfer();
			transferService.setStatus(transferTest, userRequestChoice, userStatusChoice);

			transfer2.setMoneyToTransfer(-(transferOne.getMoneyToTransfer()));
			transfer2.setUserIdTransferTo(accounts.getUserId());

			accountService.updateBalance(transferOne);
			accountService.updateBalance(transfer2);
			console.displayMessage("***Transaction complete!***");
		} else if (userStatusChoice == 3) {
			Transfer transferTest = new Transfer();
		
			
			transferService.setStatus(transferTest, userRequestChoice, userStatusChoice);
		} else {
			console.displayMessage("Thank you! Returning to Main Menu...");
		}

	}

	private void sendBucks() {
		Transfer transfer = new Transfer();
		Transfer transfer2 = new Transfer();

		Accounts accounts = accountService.showBalance();
		List<User> userNameList = userService.findAll();
		for (User user : userNameList) {
			console.displayMessage(user.getId() + ") " + user.getUsername().toUpperCase());
		}
		String transferToPrompt = "Who would you like to send money to?";
		String userInput = console.getUserInput(transferToPrompt);
		int accountToMoneyId = Integer.parseInt(userInput);
		transfer.setUserIdTransferTo(accountToMoneyId);

		transfer.setUserIdTransferFrom(accounts.getUserId());
		String amountToTransfer = "How much would you like to send?";
		String userAmountChoice = console.getUserInput(amountToTransfer);
		double userAmountAsDouble = Double.parseDouble(userAmountChoice);
		if (userAmountAsDouble > accounts.getBalance()) {
			console.displayMessage("Insufficient Funds");
			transferService.updateListOfTransfers(2, 3, accounts.getAccountId(), accountToMoneyId, userAmountAsDouble);
			mainMenu();
		}
		transfer.setMoneyToTransfer(userAmountAsDouble);

		transfer2.setMoneyToTransfer(-userAmountAsDouble);
		transfer2.setUserIdTransferTo(accounts.getUserId());

		accountService.updateBalance(transfer);
		accountService.updateBalance(transfer2);
		transferService.updateListOfTransfers(2, 1, accounts.getAccountId(), accountToMoneyId, userAmountAsDouble);
	}

	private void requestBucks() {
		Transfer transfer = new Transfer();

		Accounts accounts = accountService.showBalance();
		List<User> userNameList = userService.findAll();
		for (User user : userNameList) {
			console.displayMessage(user.getId() + ") " + user.getUsername().toUpperCase());
		}
		String transferToPrompt = "Who would you like request money from?";
		String userInput = console.getUserInput(transferToPrompt);
		int accountToMoneyId = Integer.parseInt(userInput);
		transfer.setUserIdTransferTo(accountToMoneyId);

		transfer.setUserIdTransferFrom(accounts.getUserId());
		String amountToTransfer = "How much would you like to request?";
		String userAmountChoice = console.getUserInput(amountToTransfer);
		double userAmountAsDouble = Double.parseDouble(userAmountChoice);
		transferService.updateListOfTransfers(1, 1, accounts.getAccountId(), accountToMoneyId, userAmountAsDouble);

	}

	private void exitProgram() {
		System.exit(0);
	}

	private void registerAndLogin() {
		while (!isAuthenticated()) {
			String choice = (String) console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
			if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
				register();
			} else {
				// the only other option on the login menu is to exit
				exitProgram();
			}
		}
	}

	private boolean isAuthenticated() {
		return currentUser != null;
	}

	private void register() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
		while (!isRegistered) // will keep looping until user is registered
		{
			UserCredentials credentials = collectUserCredentials();
			try {
				authenticationService.register(credentials);
				isRegistered = true;
				System.out.println("Registration successful. You can now login.");
			} catch (AuthenticationServiceException e) {
				System.out.println("REGISTRATION ERROR: " + e.getMessage());
				System.out.println("Please attempt to register again.");
			}
		}
	}

	private void login() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) // will keep looping until user is logged in
		{
			UserCredentials credentials = collectUserCredentials();
			try {
				currentUser = authenticationService.login(credentials);
			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: " + e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
	}

	private UserCredentials collectUserCredentials() {
		String username = console.getUserInput("Username");
		String password = console.getUserInput("Password");
		return new UserCredentials(username, password);
	}
}
