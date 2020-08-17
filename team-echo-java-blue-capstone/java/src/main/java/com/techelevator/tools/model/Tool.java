package com.techelevator.tools.model;

import java.sql.Date;




public class Tool {

	private int tool_id;
	private int category_id;


	private String name;
	private String category_name;
	private String description;
	private String image;
	private boolean available;
	private Date date_available;
	private long user_id;
	private String username;
	
	
	public Tool() {
		
	}
	
	public Tool(int tool_id, int category_id, String category_name, String name, String description, String image, boolean available, Date date_available, long user_id) {
		this.tool_id = tool_id;
		this.name  = name;
		this.description = description;
		this.category_id = category_id;
		this.category_name = category_name;
		this.image = image;
		this.available = available;
		this.date_available = date_available;
		this.user_id = user_id;
		
	}
	
	public Tool(Date date_available, String name, String image) {
		
		this.name  = name;
		this.image = image;
		this.date_available = date_available;
		
	}
	
	public Tool (int tool_id, int category_id, String category_name, String name, String description, String image, boolean available, Date date_available) {
		this.tool_id = tool_id;
		this.name  = name;
		this.description = description;
		this.category_id = category_id;
		this.category_name = category_name;
		this.image = image;
		this.available = available;
		this.date_available = date_available;
	}
	
	public Tool (String name, Date date_available, String username, int tool_id) {
		
		this.name = name;
		this.date_available = date_available;
		this.username = username;
		this.tool_id = tool_id;
		
	}
	
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}



	public Date getDate_available() {
		return date_available;
	}

	public void setDate_available(Date date_available) {
		this.date_available = date_available;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTool_id() {
		return tool_id;
	}

	public void setTool_id(int tool_id) {
		this.tool_id = tool_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
