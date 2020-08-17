package com.techelevator.tenmo.model;

public class Transfer {

	private int userIdTransferTo;
	private int userIdTransferFrom;
	private double moneyToTransfer;
	private int transferId;
	private int transferTypeId;
	private int transferStatusId;
	private String transferStatusDescription;
	private String transferTypeDescription;
	private String transferUsername;
	
	public int getTransferId() {
		return transferId;
	}


	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}


	public int getTransferTypeId() {
		return transferTypeId;
	}


	public void setTransferTypeId(int transferTypeId) {
		this.transferTypeId = transferTypeId;
	}


	public int getUserIdTransferTo() {
		return userIdTransferTo;
	}
	
	
	public void setUserIdTransferTo(int userIdTransferTo) {
		this.userIdTransferTo = userIdTransferTo;
	}
	
	
	public int getUserIdTransferFrom() {
		return userIdTransferFrom;
	}
	
	
	public void setUserIdTransferFrom(int userIdTransferFrom) {
		this.userIdTransferFrom = userIdTransferFrom;
	}
	
	
	public double getMoneyToTransfer() {
		return moneyToTransfer;
	}
	
	
	public void setMoneyToTransfer(double moneyToTransfer) {
		this.moneyToTransfer = moneyToTransfer;
	}


	public int getTransferStatusId() {
		return transferStatusId;
	}


	public void setTransferStatusId(int transferStatusId) {
		this.transferStatusId = transferStatusId;
	}


	public String getTransferStatusDescription() {
		return transferStatusDescription;
	}


	public void setTransferStatusDescription(String transferStatusDescription) {
		this.transferStatusDescription = transferStatusDescription;
	}


	public String getTransferTypeDescription() {
		return transferTypeDescription;
	}


	public void setTransferTypeDescription(String transferTypeDescription) {
		this.transferTypeDescription = transferTypeDescription;
	}


	public String getTransferUsername() {
		return transferUsername;
	}


	public void setTransferUsername(String transferUsername) {
		this.transferUsername = transferUsername;
	}
	
	
}
