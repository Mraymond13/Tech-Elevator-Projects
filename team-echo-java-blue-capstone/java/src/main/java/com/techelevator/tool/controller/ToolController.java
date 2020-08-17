package com.techelevator.tool.controller;

import java.security.Principal;
import java.util.List;

import org.hibernate.validator.internal.util.privilegedactions.GetAnnotationAttribute;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.User;
import com.techelevator.tool.dao.ToolListDAO;
import com.techelevator.tools.model.Category;
import com.techelevator.tools.model.Tool;

@RestController
@CrossOrigin
public class ToolController {

private	ToolListDAO toolListDAO;

public ToolController(ToolListDAO toolListDAO) {
	this.toolListDAO=toolListDAO;
	}

@RequestMapping (path = "/tools", method = RequestMethod.GET)
public List<Tool> getTools() {
	return toolListDAO.getAllTools();
}

@RequestMapping (path = "/categories", method = RequestMethod.GET)
public List<Category> getCategories() {
	return toolListDAO.getAllCategories();
	
}

@RequestMapping (path = "/tools/{id}", method = RequestMethod.GET)
public List<Tool> getUsersCheckedOutTools(@PathVariable long id) {
	return toolListDAO.getStatusAtUserId(id);
}

//@RequestMapping (path = "/checking-out", method = RequestMethod.PUT)
//public void updateList(@RequestBody Tool tool) {
//	toolListDAO.updateToolList( tool.getTool_id() );
//}

@RequestMapping (path ="/users/{id}/tools", method = RequestMethod.PUT)
public void updateUsersToolsTable(@RequestBody int[] tools, @PathVariable long id) {
	for(int i=0; i<tools.length; i++) {
	toolListDAO.insertIdsToJoinTable(tools[i], id);
}
}

@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping (path = "/checkedout", method = RequestMethod.GET)
public List<Tool> getAllCheckedOut() {
	return toolListDAO.getCheckedOutTools();
}

@RequestMapping (path ="/returns/{tool_id}", method = RequestMethod.PUT)
public void returnToolsToLibrary(@PathVariable int tool_id) {
	
	toolListDAO.returnTool(tool_id);
}

@RequestMapping (path="/users/{user_id}/tools/{tool_id}/reservations", method = RequestMethod.POST)
public void createReservation(@PathVariable int tool_id, @PathVariable int user_id) {
	toolListDAO.addReservation(tool_id, user_id);
}



}
