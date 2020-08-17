package com.techelevator.store;


import java.util.HashMap;

import java.util.Map;

public class StoreItem extends Item {
	
	private static final int STARTING_AMOUNT = 50;
	
	private Map<String, Integer> startingInventoryMap = new HashMap<String, Integer>();
	
	public StoreItem() {
		setStartingInventoryMap();
	}
	
	@Override
	public void setStartingInventoryMap() {
		readFile();
		for (String[] itemArray : super.getInventoryFileArrays()) {
			startingInventoryMap.put(itemArray[0], STARTING_AMOUNT);
		}
		 
	}
	
	@Override
	public int getAmountOfItems(String itemCode) {
		
		return startingInventoryMap.get(itemCode);
	}
		

	public void sellItem(String userProductChoice, int userQuantity) {
		
		int amountInStore = startingInventoryMap.get(userProductChoice) - userQuantity;
		
		startingInventoryMap.put(userProductChoice, amountInStore);

	}


	

}
