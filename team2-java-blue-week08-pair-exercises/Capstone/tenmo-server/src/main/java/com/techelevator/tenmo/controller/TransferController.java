package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.model.Transfer;



@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/transfers")
public class TransferController {

	private TransferDAO transfers;
	
	public TransferController(TransferDAO transferDAO) {
		this.transfers = transferDAO;
	}
	
	@RequestMapping(method= RequestMethod.POST)
	public void createTransfer(@RequestBody Transfer transfer) {
		
		transfers.updateListOfTransfers(transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getUserIdTransferFrom(), transfer.getUserIdTransferTo(), transfer.getMoneyToTransfer());
	}
	@RequestMapping(path= "/{accountId}", method= RequestMethod.GET)
	public List<Transfer> listTransfers(@PathVariable int accountId) {
		
		return transfers.getTransferHistory(accountId);
	}
	
	@RequestMapping(path = "/details/{transferId}", method= RequestMethod.GET)
	public Transfer listTransferDetails(@PathVariable int transferId) {
		return transfers.getTransferDetails(transferId);
	}
	
	@RequestMapping(path= "/{transferId}/status/{statusId}", method= RequestMethod.PUT)
	public void setStatus(@RequestBody Transfer transfer, @PathVariable int transferId, @PathVariable int statusId) {
		transfers.setStatus(transfer, transferId, statusId);
	}
}
