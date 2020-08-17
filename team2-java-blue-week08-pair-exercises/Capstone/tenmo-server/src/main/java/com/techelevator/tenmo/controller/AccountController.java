package com.techelevator.tenmo.controller;

import java.security.Principal;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.Transfer;;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountsDAO accounts;
	
	public AccountController(AccountsDAO accountsDao) {
		this.accounts = accountsDao;
	}

	@RequestMapping( method= RequestMethod.GET)
	public Accounts get(Principal principal) {
		
		return accounts.showBalance(principal.getName());

	}
	
	@RequestMapping(method= RequestMethod.PUT)
	public void updateUserBalance(@RequestBody Transfer newTransfer) {
		accounts.updateBalance(newTransfer);
	}
}
