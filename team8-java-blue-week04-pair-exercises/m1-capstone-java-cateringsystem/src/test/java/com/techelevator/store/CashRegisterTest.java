package com.techelevator.store;


import org.junit.*;

public class CashRegisterTest {
	
	CashRegister cashRegister;
	
	@Before
	public void setup() {
		cashRegister = new CashRegister();
		
	}
	
	@Test
	public void test_deposit() {
	//Testing Depositing Zero
	cashRegister.deposit(0);
	Assert.assertEquals(0, cashRegister.getBalance());

	    //Testing Depositing a Positive Amount
	    cashRegister.deposit(60000);
	    Assert.assertEquals(60000, cashRegister.getBalance());
	}

	@Test
	public void withdraw_test() {
	    
	    //Testing Withdraw Zero
	    cashRegister.withdraw(0);
	    int updatedBalance = cashRegister.getBalance();
	    Assert.assertEquals(0, updatedBalance);

	    //Testing Withdraw Method at 4000 - 300
	    cashRegister.deposit(40000);
	    cashRegister.withdraw(3000);
	    int realAmount = cashRegister.getBalance();
	    Assert.assertEquals(37000, realAmount);
	    
	    //Testing Continuous Balance Update at Different Amounts
	    cashRegister.withdraw(4000);
	    int updated = cashRegister.getBalance();
	    Assert.assertEquals(33000, updated);
	    
	    //Testing Larger Withdraw and Continuous Balance Update
	    cashRegister.withdraw(10000);
	    int lessThanUpdated = cashRegister.getBalance();
	    Assert.assertEquals(23000, lessThanUpdated);

	}
	
	@Test
	public void withdraw_nothing_test() {
		
		cashRegister.withdraw(0);
		int realAmount = cashRegister.getBalance();
		Assert.assertEquals(0, realAmount);
	}
	
	@Test
	public void test_deposit_nothing() {
		cashRegister.deposit(0);
		Assert.assertEquals(0, cashRegister.getBalance());
	}
	
	@Test
	public void get_change() {
		
	}
	
	@After 
	public void delete_output() {
		
	}
}
