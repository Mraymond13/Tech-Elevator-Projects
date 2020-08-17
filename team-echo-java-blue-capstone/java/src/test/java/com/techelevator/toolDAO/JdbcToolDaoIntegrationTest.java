package com.techelevator.toolDAO;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.dao.DAOIntegrationTest;
import com.techelevator.tool.dao.ToolListDAO;
import com.techelevator.tool.dao.toolListJDBC;
import com.techelevator.tools.model.Tool;

public class JdbcToolDaoIntegrationTest extends DAOIntegrationTest {
	
	private ToolListDAO toolListDAO;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		toolListDAO = new toolListJDBC(jdbcTemplate);
	}
	
	@Test
	public void select_tools() {
		List<Tool> tools = toolListDAO.getAllTools();
		int originalCount = tools.size();
		int added = 3;
		insertTools(added);
		int newCount = toolListDAO.getAllTools().size();
		Assert.assertEquals(originalCount + added, newCount);
	}

	private void insertTools(int cnt) {
		String sql = "INSERT INTO tools VALUES (?,?,?,?)";
		for (int i = 0; i < cnt; i++) {
			jdbcTemplate.update(sql, i, "Test Tool " + i,i,i );
		}
	}
}
