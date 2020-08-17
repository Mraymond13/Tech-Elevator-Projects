package com.techelevator.tool.controller;

import java.io.IOException;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.email.dao.EmailDDAO;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin

public class EmailController {

	private EmailDDAO emailDDAO;
	
	public EmailController(EmailDDAO emailDDAO) {
		this.emailDDAO = emailDDAO;
	}
	
	@RequestMapping(path="/emails/{tool_id}", method=RequestMethod.POST)
	public void sendEmails(@PathVariable int tool_id) throws IOException {
		emailDDAO.sendEmails(tool_id);
	}
	
}
