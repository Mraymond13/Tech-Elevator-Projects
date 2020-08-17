package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {

	public void updateListOfTransfers(int typeId, int statusId, int accountFrom, int accountTo, double amount);
	
	public List<Transfer> getTransferHistory(int accountId);

	public Transfer getTransferDetails( int transferId);
	
	public void setStatus(Transfer transfer, int transferId, int statusId);
}
