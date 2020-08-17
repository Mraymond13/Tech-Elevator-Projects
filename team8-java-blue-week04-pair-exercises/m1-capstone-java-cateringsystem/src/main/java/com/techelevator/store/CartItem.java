package com.techelevator.store;

import java.util.HashMap;
import java.util.Map;

public class CartItem extends Item {
	private static final int STARTING_AMOUNT = 0;

	private Map<String, Integer> startingInventoryMap = new HashMap<String, Integer>();

	public CartItem() {
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
	
	public int getInitialAmountOfItems(String itemCode) {
		
		return startingInventoryMap.get(itemCode);
	}

	public void addToCart(String userProductChoice, int userQuantityChoice) {
		
		int updatedCartCount = startingInventoryMap.get(userProductChoice) + userQuantityChoice;
	
		startingInventoryMap.put(userProductChoice, updatedCartCount);

	}
	
	


	public Map<String, Integer> getCartMap() {
		return startingInventoryMap;
	}

}
