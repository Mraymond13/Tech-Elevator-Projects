package com.techelevator.tool.dao;

import java.util.List;

import com.techelevator.tools.model.Category;
import com.techelevator.tools.model.Tool;

public interface ToolListDAO {

	List<Tool> getAllTools();
	List<Category> getAllCategories();
    void addReservation(int tool_id, int user_id);
	void insertIdsToJoinTable(int tool_id, long id);
	List<Tool> getCheckedOutTools();
	
	void returnTool(int tool_id);
	
	List<Tool> getStatusAtUserId(long id);
}
