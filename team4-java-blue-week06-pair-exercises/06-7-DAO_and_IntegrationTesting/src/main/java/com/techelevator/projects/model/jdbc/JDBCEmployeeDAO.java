package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		
	List<Employee> allEmployees = new ArrayList<Employee>();
		
		String selectSql = "SELECT * FROM employee";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);
		
		while( rows.next() ) {
			Employee employee = mapRowToEmployee( rows );
			allEmployees.add( employee );
		}

		return allEmployees;
	}
	
	private Employee mapRowToEmployee(SqlRowSet rows) {
		Employee employee = new Employee();
		
		employee.setId( rows.getLong("employee_id") );
		employee.setDepartmentId( rows.getLong("department_id") );
		employee.setFirstName( rows.getString("first_name") );
		employee.setLastName( rows.getString("last_name") );
		employee.setGender(rows.getString("gender").charAt(0));
		
		if ( rows.getDate("birth_date") != null) {
			employee.setBirthDay( rows.getDate("birth_date").toLocalDate() );
		}
		if ( rows.getDate("hire_date") != null) {
			employee.setHireDate( rows.getDate("hire_date").toLocalDate() );
		}
		
		
		return employee;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		
		List<Employee> employeeName = new ArrayList<Employee>();
		
		String searchEmployeeSql = "SELECT * FROM employee WHERE first_name ILIKE(?) AND last_name ILIKE(?)";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(searchEmployeeSql, firstNameSearch, lastNameSearch);
		
		while ( rows.next() ) {
			Employee additionalEmployee = mapRowToEmployee( rows );
			employeeName.add(additionalEmployee);
		}
		
		return employeeName;
	
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) {
		
		List<Employee> employeeNameById = new ArrayList<Employee>();
		
		String searchEmployeeSql = "SELECT * FROM employee WHERE department_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(searchEmployeeSql, id);
		
		while ( rows.next() ) {
			Employee idForEmployee = mapRowToEmployee( rows );
			employeeNameById.add(idForEmployee);
		}
		
		return employeeNameById;
	
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		return new ArrayList<>();
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		return new ArrayList<>();
	}

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		
	}

}
