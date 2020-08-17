package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.Transfer;

@Component
public class JDBCAccountsDAO implements AccountsDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCAccountsDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Accounts showBalance(String userName) {

		Accounts account = new Accounts();

		String sql = "SELECT accounts.user_id, accounts.account_id, accounts.balance FROM accounts JOIN users ON accounts.user_id = users.user_id WHERE users.username = ?";

		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userName);

		while (rows.next()) {
			account = mapRowToAccounts(rows);
			
		}
		return account;

	}
	
	@Override
	public Transfer updateBalance(Transfer transfer) {
		
		
		
		String sql = "UPDATE accounts SET balance = balance + ?  FROM users WHERE users.user_id = ? AND users.user_id = accounts.user_id";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, transfer.getMoneyToTransfer(), transfer.getUserIdTransferTo());
		rows.next();
		while(rows.next()) {
			transfer = mapRowToTransfer(rows);
		}
		return transfer;
	}

	private Accounts mapRowToAccounts(SqlRowSet rows) {
		Accounts account = new Accounts();

		account.setUserId(rows.getInt("user_id"));
		account.setAccountId(rows.getInt("account_id"));
		account.setBalance(rows.getDouble("balance"));

		return account;
	}
	
	private Transfer mapRowToTransfer(SqlRowSet rows) {
		Transfer transfer = new Transfer();
		
		transfer.setUserIdTransferTo(rows.getInt("account_to"));
		transfer.setUserIdTransferFrom(rows.getInt("account_from"));
		transfer.setMoneyToTransfer(rows.getDouble("amount"));
		
		return transfer;
	}
	
}
