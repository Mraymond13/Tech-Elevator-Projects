//package com.techelevator;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.SingleConnectionDataSource;
//
//import com.techelevator.excelsiorvenues.model.Venue;
//import com.techelevator.excelsiorvenues.model.VenueDAO;
//import com.techelevator.excelsiorvenues.model.jdbc.JDBCVenueDAO;
//import com.techelevator.projects.model.Department;
//import com.techelevator.projects.model.DepartmentDAO;
//import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;
//
//public abstract class DAOIntegrationTest {
//
//	
//	private VenueDAO dao;
//	private JdbcTemplate jdbcTemplate;
	/*
	 * Using this particular implementation of DataSource so that every database
	 * interaction is part of the same database session and hence the same database
	 * transaction
	 */
//	private static SingleConnectionDataSource dataSource;

	/*
	 * Before any tests are run, this method initializes the datasource for testing.
	 */
//	@BeforeClass
//	public static void setupDataSource() {
//		dataSource = new SingleConnectionDataSource();
//		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
//		dataSource.setUsername("postgres");
//		dataSource.setPassword("postgres1");
		/*
		 * The following line disables autocommit for connections returned by this
		 * DataSource. This allows us to rollback any changes after each test
		 */
//		dataSource.setAutoCommit(false);
//	}
	
	
	
//	@Before
//	public void setupTest() {
//		
//		dao = new JDBCVenueDAO(dataSource);
//		jdbcTemplate = new JdbcTemplate(datasource);
//	}

	
//	@Test
//	public void get_department_by_department_id() {
//		
//			Venue departments = getTestVenue();
//		
//	
//		List<Venue> selectedDepartment = dao.getVenue( Venue.getId() );
//		
//	
//		Assert.assertEquals(departments, selectedDepartment);
//	}
//	
//	private Venue getTestVenue() {
//		
//		Venue departments = new Venue();
//	
//		departments.setName("name"); 
//		
//		
//		return departments;
//	}
	
	/*
	 * After all tests have finished running, this method will close the DataSource
	 */
//	@AfterClass
//	public static void closeDataSource() throws SQLException {
//		dataSource.destroy();
//	}

	/*
	 * After each test, we rollback any changes that were made to the database so
	 * that everything is clean for the next test
	 */
//	@After
//	public void rollback() throws SQLException {
//		dataSource.getConnection().rollback();
//	}

	/*
	 * This method provides access to the DataSource for subclasses so that they can
	 * instantiate a DAO for testing
	 */
//	protected DataSource getDataSource() {
//		return dataSource;
//	}
//}
