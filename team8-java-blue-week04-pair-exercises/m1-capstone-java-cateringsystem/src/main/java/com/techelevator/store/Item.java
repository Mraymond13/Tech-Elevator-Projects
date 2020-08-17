package com.techelevator.store;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.FileReader;

public abstract class Item implements Sellable, Inventory, FileReader {

	private static final String FILEPATH = "cateringsystem.csv";
	private List<String[]> inventoryFileArrays = new ArrayList<String[]>();
	
	private Map<String, String> nameMap = new HashMap<String, String>();
	private Map<String, Integer> priceMap = new HashMap<String, Integer>();
	private Map<String, String> typeMap = new HashMap<String, String>();
	
	@Override
	public void readFile() {

		File file = new File(FILEPATH);

		try (Scanner fileScanner = new Scanner(file)) {

			while (fileScanner.hasNextLine()) {
				String[] fileLineArray = fileScanner.nextLine().split("\\|");
				inventoryFileArrays.add(fileLineArray);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Out of Order");
		}
		
	}
	
	@Override
	public Map<String, String> getNameMap() {
		
		for (String[] itemArray : inventoryFileArrays) {
			nameMap.put(itemArray[0], itemArray[1]);
		}
		return nameMap;
	}

	@Override
	public Map<String, Integer> getPriceMap() {
		
		for (String[] itemArray : inventoryFileArrays) {
			double priceDouble = Double.parseDouble(itemArray[2]) * 100;
			int priceInPennies = (int) priceDouble;
			priceMap.put(itemArray[0], priceInPennies);
		}
		return priceMap;
	}

	@Override
	public abstract void setStartingInventoryMap();
	
	@Override
	public Map<String, String> getFoodTypeMap() {
		
		for (String[] itemArray : inventoryFileArrays) {
			if(itemArray[3].toUpperCase().equals("A")) {
				typeMap.put(itemArray[0], "Appetizer");
			} else if (itemArray[3].toUpperCase().equals("D")) {
				typeMap.put(itemArray[0], "Dessert");
			} else if (itemArray[3].toUpperCase().equals("E")) {
				typeMap.put(itemArray[0], "Entree");
			} else {
				typeMap.put(itemArray[0], "Beverage");
			}
		}
		return typeMap;
	}

	@Override
	public String getName(String itemCode) {
		
		return nameMap.get(itemCode);
	}

	@Override
	public int getPrice(String itemCode) {
		
		return priceMap.get(itemCode);
	}

	@Override
	public abstract int getAmountOfItems(String itemCode);
	
	public String getFoodType(String itemCode) {
		return typeMap.get(itemCode);
	}


	public List<String[]> getInventoryFileArrays() {
		
		return inventoryFileArrays;
	}
	 

}
