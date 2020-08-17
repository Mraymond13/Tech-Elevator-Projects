package com.techelevator.excelsiorvenues.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.excelsiorvenues.model.Venue;
import com.techelevator.excelsiorvenues.model.VenueDAO;

public class JDBCVenueDAO implements VenueDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCVenueDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);	
		}
	
	
	@Override
	public List<Venue> getAllVenues() {
		
		List<Venue> getAllVenueNames = new ArrayList<Venue>();
		
		String selectSql = "SELECT * FROM venue";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);
		
		while( rows.next() ) {
			Venue venues = mapRowToVenue( rows );
			getAllVenueNames.add( venues );
		}
		return getAllVenueNames;
		
	}

	@Override
	public List<Venue> getVenue(Long venueId) { 
		
		List<Venue> getVenues = new ArrayList<Venue>();
		
		String sql = "SELECT venue.name, (state.name) AS state, (city.name) AS city, (venue.description) AS description, (category.name) AS category\n" + 
				"FROM venue\n" + 
				"JOIN city ON city.id = venue.city_id\n" + 
				"JOIN state ON city.state_abbreviation = state.abbreviation\n" + 
				"JOIN category_venue ON category_venue.venue_id = venue.id\n" + 
				"JOIN category ON category.id = category_venue.category_id\n" + 
				"WHERE venue.id = ? \n" + 
				"GROUP BY category.name, venue.name, state.name, city.name, venue.description\n" + 
				"ORDER BY category.name, venue.name;\n";

		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, venueId);
		
		rows.next(); 
			getVenues.add( mapRowToExtendedVenue(rows) );
		
		
		return getVenues;
	}

	private Venue mapRowToVenue(SqlRowSet rows) {
		Venue venue = new Venue();
		
		venue.setId( rows.getLong("id") );
		venue.setName( rows.getString("name") );
		venue.setCity_id( rows.getInt("city_id") );
		venue.setDescription( rows.getString("description") );

		return venue;
	}
	

	private Venue mapRowToExtendedVenue(SqlRowSet rows) {
		Venue venue = new Venue();
		
		venue.setName( rows.getString("name") );
		venue.setState(rows.getString("state"));
		venue.setCity(rows.getString("city"));
		venue.setDescription( rows.getString("description") );
		venue.setCategory(rows.getString("category"));
		
		return venue;
	}


}
