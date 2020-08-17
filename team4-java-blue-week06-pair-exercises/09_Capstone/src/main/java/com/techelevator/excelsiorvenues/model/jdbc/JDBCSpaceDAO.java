package com.techelevator.excelsiorvenues.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.excelsiorvenues.model.Space;
import com.techelevator.excelsiorvenues.model.SpaceDAO;

public class JDBCSpaceDAO implements SpaceDAO {
	
	
	private JdbcTemplate jdbcTemplate;

	public JDBCSpaceDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);	
		}
	
	
	@Override
	public List<Space> displayReservationOptions(LocalDate userInputs, int userTime, long venueId, int amountOfPeople, int userStay) {

		List<Space> newReservation = new ArrayList<Space>();

		String selectSql = "SELECT (space.id) AS number, CAST(space.daily_rate  * ? AS decimal) AS total_cost, CAST(space.daily_rate AS decimal) AS daily, (space.name) AS name, (space.max_occupancy) AS max_occupancy \n" + 
				"FROM space JOIN reservation ON reservation.space_id = space.id JOIN venue ON space.venue_id = venue.id \n" + 
				"WHERE venue.id = ? AND space.max_occupancy >= ? AND (?, ?) OVERLAPS (reservation.start_date, reservation.end_date) = FALSE \n" + 
				"GROUP BY space.id, space.name, space.daily_rate, space.max_occupancy \n" + 
				"LIMIT 5";

		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, userTime, venueId, amountOfPeople, userInputs, userInputs.plusDays(userStay -1 ));


			while (rows.next()) {
				Space spaces = mapRowToSpace(rows);
				newReservation.add(spaces);
			}
		return newReservation;
	}

	private Space mapRowToSpace(SqlRowSet rows) {
		Space space = new Space();
		space.setId(rows.getLong("number"));
		space.setTotal_cost(rows.getDouble("total_cost"));
		space.setDaily_rate(rows.getDouble("daily"));
		space.setName(rows.getString("name"));
		space.setMax_occupancy(rows.getInt("max_occupancy"));

		
		return space;
		
	}
	
	@Override
	public List<Space> getAllSpacesAtVenueId(long id) {
		
		List<Space> spacesAtVenueId = new ArrayList<Space>();
		
		String searchEmployeeSql = "SELECT (space.id) AS id, (space.name) AS name, (space.is_accessible) AS accesible, (space.open_from) AS open, "
				+ "(space.open_to) AS close, CAST(space.daily_rate AS decimal) AS rate, (space.max_occupancy) AS occupancy "
				+ "FROM space JOIN venue ON venue.id = space.venue_id "
				+ "WHERE venue.id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(searchEmployeeSql, id);
		
		while ( rows.next() ) {
			Space idForSpace = mapRowToSpaceAtId( rows );
			spacesAtVenueId.add(idForSpace);
		}
		
		return spacesAtVenueId;
	
	}
	
public List<Space> getAllSpacesAtSpaceId(long id) {
		
		List<Space> spacesAtSpaceId = new ArrayList<Space>();
		
		String searchEmployeeSql = "SELECT name FROM space WHERE id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(searchEmployeeSql, id);
		
		while ( rows.next() ) {
			Space idForSpace = mapRowToSpaceAtId( rows );
			spacesAtSpaceId.add(idForSpace);
		}
		
		return spacesAtSpaceId;
	
	}
	
	private Space mapRowToSpaceAtId(SqlRowSet rows) {
		
		Space space = new Space();
		
		space.setId(rows.getLong("id"));
		space.setName(rows.getString("name"));
		space.setIs_accesible(rows.getString("accesible"));
		space.setOpen_from(rows.getInt("open"));
		space.setOpen_to(rows.getInt("close"));
		space.setDaily_rate(rows.getDouble("rate"));
		space.setMax_occupancy(rows.getInt("occupancy"));
		
		return space;
	}

}
