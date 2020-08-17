package com.techelevator.store;

public interface Sellable {
	
	public String getName(String itemCode);
	
	public int getPrice(String itemCode);
	
	public int getAmountOfItems(String itemCode);

	
}
