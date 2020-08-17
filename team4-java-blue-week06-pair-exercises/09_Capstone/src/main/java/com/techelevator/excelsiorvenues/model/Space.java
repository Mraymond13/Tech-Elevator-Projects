package com.techelevator.excelsiorvenues.model;

public class Space {
	
	
	private Long id;
	private int venue_id;
	private String name;
	private String is_accesible;
	private int open_from;
	private int open_to;
	private double daily_rate;
	private int max_occupancy;
	private double total_cost;
	
	
	
	
	
	
	
	public double getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getVenue_id() {
		return venue_id;
	}
	public void setVenue_id(int venue_id) {
		this.venue_id = venue_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIs_accesible() {
		return is_accesible;
	}
	public void setIs_accesible(String is_accesible) {
		this.is_accesible = is_accesible;
	}
	public int getOpen_from() {
		return open_from;
	}
	public void setOpen_from(int open_from) {
		this.open_from = open_from;
	}
	public int getOpen_to() {
		return open_to;
	}
	public void setOpen_to(int open_to) {
		this.open_to = open_to;
	}
	public double getDaily_rate() {
		return daily_rate;
	}
	public void setDaily_rate(double daily_rate) {
		this.daily_rate = daily_rate;
	}
	public int getMax_occupancy() {
		return max_occupancy;
	}
	public void setMax_occupancy(int max_occupancy) {
		this.max_occupancy = max_occupancy;
	}
	
	
	
}
