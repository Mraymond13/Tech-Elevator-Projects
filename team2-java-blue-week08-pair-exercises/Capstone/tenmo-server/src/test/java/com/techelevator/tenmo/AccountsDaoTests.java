package com.techelevator.tenmo;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.sql.SQLException;

import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountsDaoTests {
	private static SingleConnectionDataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	
	@BeforeTestClass
	public  void setupDataSource() {
		
		dataSource = new SingleConnectionDataSource();
		
		dataSource.setUrl("jdbc:postgresql://localhost:8080/tenmo");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		dataSource.setAutoCommit(false);
	}

	@AfterTestClass
	public static void destroyDataSource() {
		dataSource.destroy();
	}
	
	@AfterEach
	public void rollbackTransaction() throws SQLException {
		dataSource.getConnection().rollback();
	}
	@BeforeEach 
	public void setupTest() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		

	}
	
	@Test
	public void testTest() {
		
	}
}
