package com.techelevator.tenmo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.User;




@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/users")

public class UserController {

	private UserDAO users;
	
	public UserController(UserDAO userDAO) {
		this.users = userDAO;
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public List<User> getAllUsers() {
		return users.findAll();
	}

	
}
