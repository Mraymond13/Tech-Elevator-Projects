package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.models.Accounts;
import com.techelevator.tenmo.models.Transfer;


public interface AccountsDAO {
	
	
	public Accounts showBalance();
	
	public Transfer updateBalance(Transfer transfer);

}
