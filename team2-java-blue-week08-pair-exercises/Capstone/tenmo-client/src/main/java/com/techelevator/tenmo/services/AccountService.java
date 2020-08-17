package com.techelevator.tenmo.services;




import java.io.Console;
import java.util.Arrays;
import java.util.List;

import org.bouncycastle.crypto.tls.HandshakeType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.models.Accounts;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.view.ConsoleService;


public class AccountService implements AccountsDAO{
	private ConsoleService console;
	private String jwt;
	private String baseUrl;
	private RestTemplate restTemplate = new RestTemplate();
	
	public AccountService(String jwt, String baseUrl) {
		this.baseUrl = baseUrl;
		this.jwt = jwt;
	}
	
	
	

	@Override
	public Accounts showBalance() {
		String url = baseUrl + "accounts";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwt);
		HttpEntity request = new HttpEntity<>(headers);
		
		Accounts account = null;
		try {
			account = restTemplate.exchange(url, HttpMethod.GET, request, Accounts.class).getBody();
		} catch(RestClientResponseException ex) {
			console.displayError(ex.getRawStatusCode() + " : " +  ex.getStatusText());
		}catch (ResourceAccessException ex) {
		      console.displayError(ex.getMessage());
		}
		return account;
	}




	@Override
	public Transfer updateBalance(Transfer transfer) {
		String url = baseUrl + "accounts";
		if (transfer == null) {
			return null;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwt);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Transfer> request = new HttpEntity<>(transfer, headers);
		
		try {
			restTemplate.put(url, request);
		} catch (RestClientException ex) {
			
		}
		return transfer;
	}

}
