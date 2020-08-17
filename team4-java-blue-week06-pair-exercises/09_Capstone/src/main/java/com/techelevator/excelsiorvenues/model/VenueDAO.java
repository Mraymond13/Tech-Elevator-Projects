package com.techelevator.excelsiorvenues.model;


import java.util.List;

public interface VenueDAO {

	public List<Venue> getAllVenues();
	
	public List<Venue> getVenue(Long venueId);
	

}
