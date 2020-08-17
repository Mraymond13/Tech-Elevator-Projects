package com.techelevator.email;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import com.techelevator.email.dao.EmailDDAO;
import com.techelevator.model.User;
import com.techelevator.tools.model.Tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JavaEmail implements EmailDDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JavaEmail(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
   public void sendEmails(int tool_id) throws IOException {
	   List<User> status = new ArrayList<User>();
	   String email = "SELECT users.email_address FROM reservation JOIN users ON users.user_id = reservation.user_id WHERE reservation.tool_id = ?";
       
	   SqlRowSet rows = jdbcTemplate.queryForRowSet(email, tool_id);
		
		while (rows.next()) {
			
			status.add( new User (rows.getString("email_address")));
			
		}
		
		for(User a: status) {
			
			
		Email to = new Email(a.getEmail_address()); // use your own email address here	 
		
		Email from = new Email("toolbxnoreply@gmail.com");
       

       String subject = "Your Reserved Tool Is Available";
       Content content = new Content("text/html", "Hey! Your toolbx tool is available. Log in and reserve today!");

       Mail mail = new Mail(from, subject, to, content);

       SendGrid sg = new SendGrid("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");
       Request request = new Request();

       request.setMethod(Method.POST);
       request.setEndpoint("/mail/send");
       request.setBody(mail.build());

       Response response = sg.api(request);

       System.out.println(response.getStatusCode());
       System.out.println(response.getHeaders());
       System.out.println(response.getBody());
   }
		}
	   
	 

}