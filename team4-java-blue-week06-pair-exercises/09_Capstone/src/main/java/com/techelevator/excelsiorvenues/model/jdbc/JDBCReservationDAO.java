package com.techelevator.excelsiorvenues.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.excelsiorvenues.model.Reservation;
import com.techelevator.excelsiorvenues.model.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private Reservation mapRowToReservation(SqlRowSet rows) {
		Reservation reservation = new Reservation();

		reservation.setReservation_id(rows.getLong("reservation_id"));
		

		return reservation;
	}
	
	@Override
	public List<Reservation> getNewReservationKey(int spaceReserved) {
		
		List<Reservation> getReservationKey = new ArrayList<Reservation>();
		
		
		String selectSql = "SELECT reservation_id FROM reservation WHERE space_id = ?";
		
	
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, spaceReserved);
	
		rows.next(); 
		getReservationKey.add( mapRowToReservation(rows));
		
	return getReservationKey;

		}
	
	
		@Override
	public void bookReservation(int spaceReserved, int amountOfPeople, LocalDate userInputs, int userStay, String nameOfReserver) {

		String insertSql = "INSERT INTO reservation VALUES (DEFAULT, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(insertSql, spaceReserved, amountOfPeople, userInputs, userInputs.plusDays(userStay),
				nameOfReserver);

	}

}
