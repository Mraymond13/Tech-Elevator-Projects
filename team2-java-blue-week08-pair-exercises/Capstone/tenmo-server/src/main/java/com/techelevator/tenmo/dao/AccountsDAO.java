package com.techelevator.tenmo.dao;



import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.Transfer;

public interface AccountsDAO {
	
	
	public Accounts showBalance(String userName);
	
	public Transfer updateBalance(Transfer transfer); 
		
	


}
