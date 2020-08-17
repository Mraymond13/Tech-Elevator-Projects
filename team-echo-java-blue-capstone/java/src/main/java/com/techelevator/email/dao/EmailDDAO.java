package com.techelevator.email.dao;

import java.io.IOException;

public interface EmailDDAO {

	void sendEmails(int tool_id) throws IOException;
}
