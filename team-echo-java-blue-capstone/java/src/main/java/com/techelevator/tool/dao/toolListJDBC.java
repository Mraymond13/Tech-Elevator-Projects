package com.techelevator.tool.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.techelevator.tools.model.Category;
import com.techelevator.tools.model.Tool;

@Service
@Component
public class toolListJDBC implements ToolListDAO {

	private JdbcTemplate jdbcTemplate;

	public toolListJDBC(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Tool> getAllTools() {
		List<Tool> tools = new ArrayList<Tool>();
		String selectSql = "SELECT tools.tool_id, tools.name, categories.name as category_name, categories.category_id, description, image, available, date_available FROM tools " + 
				"JOIN tool_category on tools.tool_id = tool_category.tool_id " + 
				"Join categories on categories.category_id = tool_category.category_id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);

		while (rows.next()) {
			tools.add(new Tool(rows.getInt("tool_id"), rows.getInt("category_id"), rows.getString("category_name"), rows.getString("name"), rows.getString("description"),
					rows.getString("image"), rows.getBoolean("available"), rows.getDate("date_available")));
		}
		return tools;
	}
	
	

	@Override
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		String selectSql = "Select * from categories";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);

		while (rows.next()) {
			categories.add(new Category(rows.getInt("category_id"), rows.getString("name")));
		}
		return categories;
	}

//	@Override
//	public void updateToolList(int id, int user_id) {
//		String selectSql = "UPDATE tools SET available = 'false', date_available = CURRENT_DATE + 7, user_id = ? WHERE tool_id = ?";
//			jdbcTemplate.update(selectSql, id, user_id);
//		
//	}

	@Override
	public void insertIdsToJoinTable(int tool_id, long id) {
		
		String selectSql = "UPDATE tools SET available = 'false', date_available = CURRENT_DATE + 7, user_id = ? WHERE tool_id = ?";
		
		 jdbcTemplate.update(selectSql, id, tool_id );
		
	}

	@Override
	public List<Tool> getStatusAtUserId(long id) {
		List<Tool> status = new ArrayList<Tool>();
		
		String selectSql = "SELECT date_available, name, image FROM tools WHERE user_id = ?";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, id);
		
		while (rows.next()) {
			
			status.add( new Tool (rows.getDate("date_available"), rows.getString("name"), rows.getString("image")));
			
		}
		return status;
	}
	@Secured("ROLE_ADMIN")
	@Override
	public List<Tool> getCheckedOutTools() {
		
		List<Tool> checkedOut = new ArrayList<Tool>();
		String selectSql = "SELECT tools.name, tools.date_available, users.username, tools.tool_id FROM tools " + 
				"JOIN users ON users.user_id = tools.user_id WHERE available = 'false'";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);

		while (rows.next()) {
			checkedOut.add(new Tool(rows.getString("name"), rows.getDate("date_available"), rows.getString("username"), rows.getInt("tool_id")));
		}
		return checkedOut;
	}

	@Override
	public void returnTool(int tool_id) {
		
		String selectSql = "UPDATE tools SET available ='true', date_available = null, user_id = null WHERE tool_id = ?";
		
		 jdbcTemplate.update(selectSql, tool_id );
		
	}
	
	@Override 
	public void addReservation(int tool_id, int user_id) {
		
		
		String selectSql = "INSERT INTO reservation VALUES (default, ?, ?)";
		
		jdbcTemplate.update(selectSql, tool_id, user_id);
	}
		
	
		
	}
	
	
	
	
	
	

