package com.techelevator.excelsiorvenues.model;

import java.time.LocalDate;
import java.util.List;


public interface ReservationDAO {
	
	public List<Reservation> getNewReservationKey(int spaceReserved);
	
	public void bookReservation(int spaceReserved, int amountOfPeople, LocalDate userInputs, int userStay, String nameOfReserver);
	
	

}

