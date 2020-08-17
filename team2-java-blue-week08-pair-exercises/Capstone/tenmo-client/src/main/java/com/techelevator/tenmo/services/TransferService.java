package com.techelevator.tenmo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.view.ConsoleService;

public class TransferService implements TransferDAO {

	private String jwt;
	private String baseUrl;
	private RestTemplate restTemplate = new RestTemplate();
	private ConsoleService console;
	
	public TransferService(String jwt, String baseUrl) {
		this.baseUrl = baseUrl;
		this.jwt = jwt;
	}

	@Override
	public void updateListOfTransfers(int typeId, int statusId, int accountFrom, int accountTo, double amount) {
		Transfer transfer = new Transfer();
		transfer.setTransferTypeId(typeId);
		transfer.setTransferStatusId(statusId);
		transfer.setUserIdTransferTo(accountTo);
		transfer.setUserIdTransferFrom(accountFrom);
		transfer.setMoneyToTransfer(amount);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwt);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Transfer> request = new HttpEntity<>(transfer, headers);
		
		try {
			restTemplate.postForObject(baseUrl + "transfers", request, Transfer.class);
		} catch(RestClientResponseException ex) {
			console.displayError(ex.getRawStatusCode() + " : " +  ex.getStatusText());
		}catch (ResourceAccessException ex) {
		      console.displayError(ex.getMessage());
		}
		
	}

	@Override
	public List<Transfer> getTransferHistory(int accountId) {
		
		
		String url = baseUrl + "transfers/";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwt);
		HttpEntity request = new HttpEntity<>(headers);
		Transfer[] transferArray = null;
		List<Transfer> transferList = new ArrayList<Transfer>();
		try {
			transferArray = restTemplate.exchange(url + accountId, HttpMethod.GET, request, Transfer[].class).getBody();
			transferList = Arrays.asList(transferArray);
		} catch(RestClientResponseException ex) {
			console.displayError(ex.getRawStatusCode() + " : " +  ex.getStatusText());
		}catch (ResourceAccessException ex) {
		      console.displayError(ex.getMessage());
		}
		return transferList;
	}

	@Override
	public Transfer getTransferDetails( int transferId) {
		String url = baseUrl + "transfers/details/";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwt);
		HttpEntity request = new HttpEntity<>(headers);
		Transfer transfer = new Transfer();
		transfer = restTemplate.exchange(url + transferId,  HttpMethod.GET, request, Transfer.class).getBody();
		
		return transfer;
	}

	@Override
	public void setStatus(Transfer transfer, int transferId, int statusId) {
		String url = baseUrl + "transfers/" + transferId + "/status/" + statusId;
		
		transfer.setTransferStatusId(statusId);
		transfer.setTransferId(transferId);
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwt);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Transfer> request = new HttpEntity<>(transfer, headers);
		
//		try {
			restTemplate.put(url, request);
//		} catch(RestClientResponseException ex) {
//			console.displayError(ex.getRawStatusCode() + " : " +  ex.getStatusText());
//		}catch (ResourceAccessException ex) {
//		      console.displayError(ex.getMessage());
//		}
		
	}
	
	
}
