package com.techelevator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.excelsiorvenues.model.Reservation;
import com.techelevator.excelsiorvenues.model.ReservationDAO;
import com.techelevator.excelsiorvenues.model.Space;
import com.techelevator.excelsiorvenues.model.SpaceDAO;
import com.techelevator.excelsiorvenues.model.Venue;
import com.techelevator.excelsiorvenues.model.VenueDAO;
import com.techelevator.excelsiorvenues.model.jdbc.JDBCReservationDAO;
import com.techelevator.excelsiorvenues.model.jdbc.JDBCSpaceDAO;
import com.techelevator.excelsiorvenues.model.jdbc.JDBCVenueDAO;

public class ExcelsiorCLI {

	private static final String MAIN_MENU_OPTION_LIST_VENUES = "List Venues";
	private static final String MAIN_MENU_OPTION_QUIT = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_LIST_VENUES,
			MAIN_MENU_OPTION_QUIT, };

	private static final String MENU_OPTION_RETURN_TO_PREVIOUS = "Return to Previous Screen";
	private static final String MENU_OPTION_SEE_VENUE_DETAILS = "Press 1 to pick a Venue";
	private static final String[] VENUE_MENU_OPTIONS = new String[] { MENU_OPTION_SEE_VENUE_DETAILS,
			MENU_OPTION_RETURN_TO_PREVIOUS };

	
	private static final String EMPL_MENU_OPTION_VIEW_SPACES = "View Spaces";
	private static final String EMPL_MENU_OPTION_SEARCH_FOR_RESERVATION = "Search for Reservation";
	private static final String EMPL_MENU_OPTION_RETURN_TO_LAST_SCREEN = "Return to Previous Screen";
	private static final String[] EMPL_MENU_OPTIONS = new String[] { EMPL_MENU_OPTION_VIEW_SPACES, EMPL_MENU_OPTION_SEARCH_FOR_RESERVATION,
			EMPL_MENU_OPTION_RETURN_TO_LAST_SCREEN, };

	Scanner in = new Scanner(System.in);

	private Menu menu;
	private VenueDAO venueDAO;
	private SpaceDAO spaceDAO;
	private ReservationDAO reservationDAO;

	public static void main(String[] args) {
		ExcelsiorCLI application = new ExcelsiorCLI();
		application.run();
	}

	private JdbcTemplate jdbcTemplate;

	public ExcelsiorCLI() {

		this.menu = new Menu(System.in, System.out);

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		

		venueDAO = new JDBCVenueDAO(dataSource);
		spaceDAO = new JDBCSpaceDAO(dataSource);
		reservationDAO = new JDBCReservationDAO(dataSource);
	}
	

	public ExcelsiorCLI(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	private void run() {
		while (true) {
			printHeading("What would you like to do?");
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_LIST_VENUES)) {
				handleDepartments();
			}
		}
	}

	private void handleDepartments() {
		handleListAllDepartments();
		printHeading("Pick a Venue to view details");
		String choice = (String) menu.getChoiceFromOptions(VENUE_MENU_OPTIONS);
		if (choice.equals(MENU_OPTION_SEE_VENUE_DETAILS)) {
			listVenueDetails();
		}	
	}

	private void handleListAllDepartments() {
		printHeading("All Venues");
		List<Venue> allVenues = venueDAO.getAllVenues();
		listVenues(allVenues);
	}

	private void listVenueDetails() {
		
		printHeading("What venue would you like to see?");
		System.out.println("Enter number here >>>>>>>>");
		String userInput = in.nextLine();
		Long venueId = Long.parseLong(userInput);
		
		List<Venue> venueDetails = venueDAO.getVenue(venueId);
		printVenueDetails(venueDetails);
		
		printHeading("What would you like to do next?");
		String choice = (String) menu.getChoiceFromOptions(EMPL_MENU_OPTIONS);
		
		
		if (choice.equals(EMPL_MENU_OPTION_VIEW_SPACES)) {
			
			printHeading("All Spaces");
			System.out.println();
			printHeading("Space Id      Wheelchair Accesible          Name                                     Open From          Open To         Daily Rate          Max Occupancy");
			List<Space> allSpaces = spaceDAO.getAllSpacesAtVenueId(venueId);
			listSpaces(allSpaces);
			
		} else if (choice.equals(EMPL_MENU_OPTION_SEARCH_FOR_RESERVATION)) {
			
			printHeading("Reserve a Space");
			System.out.println("When do you need the space?");
			String userInpu = in.nextLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy/MM/dd");
			LocalDate userInputs = LocalDate.parse(userInpu, formatter);
			System.out.println("How many days will you need the space?");
			String userDays = in.nextLine();
			int userTime = Integer.parseInt(userDays);
			int userStay = userTime + 1;

			System.out.println("How many people will be in attendance");
			String userAttendance = in.nextLine();
			int amountOfPeople = Integer.parseInt(userAttendance);
			printHeading("Top 5 available Spaces");
			printHeading("Space Id    Space Name    Daily Rate   Max Occupancy   Total Cost");
			List<Space> topFiveSpace = spaceDAO.displayReservationOptions(userInputs, userTime, venueId, amountOfPeople, userStay);
			
			reservationSpaces(topFiveSpace);
			printHeading("Which space would you like to reserve?");
			String spaceToReserve = in.nextLine();
			Integer spaceReserved = Integer.parseInt(spaceToReserve);
			System.out.println("Who is this reservation for?");
			String nameOfReserver = in.nextLine();
			printHeading("");
			System.out.println("Thank you for submitting your reservation! The details for the event are listed below: ");
			reservationDAO.bookReservation(spaceReserved, amountOfPeople, userInputs, userStay, nameOfReserver);
			System.out.println("Confirmation #" + reservationDAO.getNewReservationKey(spaceReserved));
			List<Venue> venueForReservation = venueDAO.getVenue(venueId);
			System.out.println("Venue:" + " ");
			listReservedVenues(venueForReservation);

			System.out.println("Space" + " ");
			reservationSpace(topFiveSpace);
			System.out.println("Reserved For: " + " " + nameOfReserver);
			System.out.println("Number Of Attendees: " + "  " + amountOfPeople);
			System.out.println("Arrival Date:" + "  " + userInputs);
			System.out.println("Departure Date: " + "  " +  userInputs.plusDays(userStay -1 ));
			System.out.println("Total Cost: " + " " );
			reservationSpaceTotalCost(topFiveSpace);

		}
	}

	private void listVenues(List<Venue> venues) {
		System.out.println();
		if (venues.size() > 0) {
			for (Venue ven : venues) {
				System.out.println(ven.getId() + ") " + ven.getName());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void listReservedVenues(List<Venue> newVenue) {
		System.out.println();
		if (newVenue.size() > 0) {
			for (Venue ven : newVenue) {
				System.out.println(ven.getName());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void printVenueDetails(List<Venue> venueDetails) {
		System.out.println();
		if (venueDetails.size() > 0) {
			for (Venue ven : venueDetails) {
				System.out.println(ven.getName() + "    "  + ven.getDescription() +  "    " + ven.getCity()
						+  ",  " + ven.getState() + "     " + ven.getCategory());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void reservationSpaces(List<Space> spaceDetails) {
		System.out.println();
		if (spaceDetails.size() > 0) {
			for (Space ven : spaceDetails) {
				System.out.println(ven.getId() + "    " + ven.getName() + "     $" + ven.getDaily_rate() + "         "
						+ ven.getMax_occupancy() + "         $" + ven.getTotal_cost());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}
	
	private void reservationSpaceTotalCost(List<Space> spaceCost) {
		System.out.println();
		if (spaceCost.size() > 0) {
			for (Space ven : spaceCost) {
				System.out.println(ven.getTotal_cost());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void reservationSpace(List<Space> spaceName) {
		System.out.println();
		if (spaceName.size() > 0) {
			for (Space ven : spaceName) {
				System.out.println(ven.getName());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void printReservationDetails(List<Reservation> reserved) {
		System.out.println();
		if (reserved.size() > 0) {
			for (Reservation ven : reserved) {
				System.out.println(ven.getReservation_id() + " " + ven.getNumber_of_attendees() + " "
						+ ven.getStart_date() + "   " + ven.getEnd_date());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void listSpaces(List<Space> spaces) {
		System.out.println();
		if (spaces.size() > 0) {
			for (Space ven : spaces) {
				System.out.println(
						ven.getId() + "             " + ven.getIs_accesible() + "                           " + ven.getName() + "                          " + ven.getOpen_from()
								+ "                   " + ven.getOpen_to() + "              $" + ven.getDaily_rate() + "              " + ven.getMax_occupancy());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	@SuppressWarnings("resource")
	private String getUserInput(String prompt) {
		System.out.print(prompt + " >>> ");
		return new Scanner(System.in).nextLine();
	}

}
