package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.ProjectDAO;

public class JDBCProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Project> getAllActiveProjects() {
		
		List<Project> allProjects = new ArrayList<Project>();
		
		String selectSql = "SELECT * FROM project WHERE from_date IS NOT NULL AND to_date > now() OR to_date IS NULL";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);
		
		while( rows.next() ) {
			Project project = mapRowToProject( rows );
			allProjects.add( project );
		}

		return allProjects;
		
		
	}
	
	private Project mapRowToProject(SqlRowSet rows) {
		Project project = new Project();
		
		project.setId( rows.getLong("project_id") );
		project.setName( rows.getString("name") );
		
		if ( rows.getDate("from_date") != null) {
			project.setStartDate( rows.getDate("from_date").toLocalDate() );
		}
		if (rows.getDate("to_date") != null) {
			project.setEndDate(rows.getDate("to_date").toLocalDate() );
		}
		return project;
	}

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		String insertSql = "DELETE FROM project_employee WHERE employee_id = ? AND project_id = ?";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(insertSql, projectId, employeeId);
	
			rows.next();
	}

	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		
	String insertSql = "INSERT INTO project_employee (project_id, employee_id) VALUES (?, ?) RETURNING project_id";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(insertSql, projectId, employeeId);
	
			rows.next();
		
	}
	

}
