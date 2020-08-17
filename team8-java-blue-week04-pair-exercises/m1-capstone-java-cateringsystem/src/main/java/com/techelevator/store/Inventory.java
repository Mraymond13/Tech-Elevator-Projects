package com.techelevator.store;

import java.util.Map;

public interface Inventory {

	public Map<String, String> getNameMap();

	public Map<String, Integer> getPriceMap();

	public void setStartingInventoryMap();

	public Map<String, String> getFoodTypeMap();
}
