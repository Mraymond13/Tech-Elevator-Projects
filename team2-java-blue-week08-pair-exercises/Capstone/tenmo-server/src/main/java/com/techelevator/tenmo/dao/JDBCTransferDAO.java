package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Transfer;

@Component
public class JDBCTransferDAO implements TransferDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCTransferDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void updateListOfTransfers(int typeId, int statusId, int accountFrom, int accountTo, double amount) {
		
		
		String sql = "INSERT INTO transfers VALUES(DEFAULT, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, typeId, statusId, accountFrom, accountTo, amount );
		
	}

	@Override
	public List<Transfer> getTransferHistory(int accountId) {
		List<Transfer> transfers = new ArrayList<Transfer>();
		
		String sql = "SELECT * FROM transfers JOIN accounts ON transfers.account_to = accounts.account_id JOIN users ON accounts.user_id = users.user_id WHERE account_to= ? OR account_from= ?";
		
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, accountId, accountId);
		
		
		while(rows.next()) {
			transfers.add(mapRowToTransfer(rows));
		}
		
		return transfers;
	}
	@Override
	public Transfer getTransferDetails(int transferId) {
		Transfer transfers = new Transfer();
		
		String sql = "SELECT * FROM transfers JOIN accounts ON transfers.account_to = accounts.account_id JOIN users ON accounts.user_id = users.user_id WHERE transfer_id = ?";
		
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, transferId);
		
		
		rows.next();
			transfers = (mapRowToTransfer(rows));
		
		
		return transfers;
	}
	
	@Override
	public void setStatus(Transfer transfer, int transferId, int statusId) {
		
		String sql = "UPDATE transfers SET transfer_status_id = ? FROM accounts WHERE transfer_id = ? AND transfers.account_from = accounts.account_id";
		
		jdbcTemplate.update(sql, statusId, transferId);
	}
	
	
	private Transfer mapRowToTransfer(SqlRowSet rows) {
		Transfer transfer = new Transfer();
		
		transfer.setUserIdTransferTo(rows.getInt("account_to"));
		transfer.setUserIdTransferFrom(rows.getInt("account_from"));
		transfer.setMoneyToTransfer(rows.getDouble("amount"));
		transfer.setTransferId(rows.getInt("transfer_id"));
		transfer.setTransferTypeId(rows.getInt("transfer_type_id"));
		transfer.setTransferStatusId(rows.getInt("transfer_status_id"));
		transfer.setTransferUsername(rows.getString("username"));
		return transfer;
	}

	

	
}
