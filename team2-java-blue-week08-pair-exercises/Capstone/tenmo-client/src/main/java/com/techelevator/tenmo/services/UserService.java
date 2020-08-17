package com.techelevator.tenmo.services;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.models.User;
import com.techelevator.view.ConsoleService;

public class UserService  implements UserDAO{

	private ConsoleService console;
	private String jwt;
	private String baseUrl;
	private RestTemplate restTemplate = new RestTemplate();
	
	public UserService(String jwt, String baseUrl) {
		this.baseUrl = baseUrl;
		this.jwt = jwt;
	}
	
	@Override
	public List<User> findAll() {
		String url = baseUrl + "users";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwt);
		HttpEntity request = new HttpEntity<>(headers);
		User[] userArray = null;
		List<User> userList = new ArrayList<User>();
		try {
			userArray = restTemplate.exchange(url, HttpMethod.GET, request, User[].class).getBody();
			userList = Arrays.asList(userArray);
		} catch(RestClientResponseException ex) {
			console.displayError(ex.getRawStatusCode() + " : " +  ex.getStatusText());
		}catch (ResourceAccessException ex) {
		      console.displayError(ex.getMessage());
		}
		
		return userList;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findIdByUsername(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean create(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
