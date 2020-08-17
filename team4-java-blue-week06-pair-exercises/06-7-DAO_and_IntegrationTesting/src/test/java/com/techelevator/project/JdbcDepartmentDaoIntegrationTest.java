package com.techelevator.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;
import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;



public class JdbcDepartmentDaoIntegrationTest {
private static SingleConnectionDataSource datasource;
	
	private DepartmentDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	
	@BeforeClass
	public static void setupDataSource() {
		
		datasource = new SingleConnectionDataSource();
		datasource.setUrl("jdbc:postgresql://localhost:5432/projects");
		datasource.setUsername("postgres");
		datasource.setPassword("postgres1");
		datasource.setAutoCommit( false );
	}
	
	
	@AfterClass
	public static void destroyDataSource() {
		
		datasource.destroy();
	}
	
	@After
	public void rollbackTransaction() throws SQLException {
		datasource.getConnection().rollback();
	}
	
	
	@Before
	public void setupTest() {
		
		dao = new JDBCDepartmentDAO(datasource);
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Test
	public void get_department_by_department_id() {
		
			Department departments = getTestDepartment();
			
		dao.createDepartment(departments);
		
	
		Department selectedDepartment = dao.getDepartmentById( departments.getId() );
		
	
		Assert.assertEquals(departments, selectedDepartment);
	}
	
	private Department getTestDepartment() {
		
		Department departments = new Department();
	
		departments.setName("name"); 
		
		
		return departments;
	}
	
	@Test
	public void get_all_departments_test() {
	
		List<Department> department = dao.getAllDepartments();
		int originalCount = department.size();
		
			
		Department departments = getTestDepartment();
		dao.createDepartment( departments );
		
		
		department = dao.getAllDepartments();
		
		Assert.assertEquals(originalCount + 1, department.size());
	}
	
	@Test
	public void test_search_department_by_name() {
		
		Department departments = getTestDepartment();
		
		dao.createDepartment(departments);
		
		List<Department> department = dao.searchDepartmentsByName(departments.getName());
	
		Assert.assertEquals(departments, department.get(0));
	}

}
