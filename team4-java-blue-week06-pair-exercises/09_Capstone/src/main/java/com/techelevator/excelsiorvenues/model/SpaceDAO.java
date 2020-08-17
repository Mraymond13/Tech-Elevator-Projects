package com.techelevator.excelsiorvenues.model;

import java.time.LocalDate;
import java.util.List;

public interface SpaceDAO {
	
	public List<Space> displayReservationOptions(LocalDate userInputs, int userTime, long venueId, int amountOfPeople, int userStay);
	
	public List<Space> getAllSpacesAtVenueId(long id);
	
	public List<Space> getAllSpacesAtSpaceId(long id);

	

}
