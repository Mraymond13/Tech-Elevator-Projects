package com.techelevator.store;

import org.junit.*;

public class StoreItemTest {

	private StoreItem storeItem;

	@Before
	public void setup() {
		storeItem = new StoreItem();
		storeItem.readFile();
		storeItem.setStartingInventoryMap();
	}

	@Test
	public void get_starting_string_of_fifty_items() {

		// Testing starting amount of B2
		Assert.assertEquals(50, storeItem.getAmountOfItems("B2"));

		// Testing starting amount of A3
		Assert.assertEquals(50, storeItem.getAmountOfItems("A3"));

		// Testing starting amount of D1
		Assert.assertEquals(50, storeItem.getAmountOfItems("D1"));

		// Testing starting amount of E2
		Assert.assertEquals(50, storeItem.getAmountOfItems("E2"));
	}

	@Test
	public void sell_item_test() {

		// Testing selling B4 at amount 30
		storeItem.sellItem("B4", 30);
		Assert.assertEquals(20, storeItem.getAmountOfItems("B4"));

		// Testing selling A1 at max amount
		storeItem.sellItem("A1", 50);
		Assert.assertEquals(0, storeItem.getAmountOfItems("A1"));

		// Testing selling E2 at lowest amount
		storeItem.sellItem("E2", 1);
		Assert.assertEquals(49, storeItem.getAmountOfItems("E2"));

		// Testing selling D3 at max amount
		storeItem.sellItem("D3", 50);
		Assert.assertEquals(0, storeItem.getAmountOfItems("D3"));
	}

	@Test
	public void get_name() {
		// Testing Get Name From Code B4
		storeItem.getNameMap();
		Assert.assertEquals("Sparkling Water", storeItem.getName("B4"));

		// Testing Get Name From Code E1
		storeItem.getNameMap();
		Assert.assertEquals("Baked Chicken", storeItem.getName("E1"));

		// Testing Get Name From Code D2
		storeItem.getNameMap();
		Assert.assertEquals("Cake", storeItem.getName("D2"));

		// Testing Get Name From Code A2
		storeItem.getNameMap();
		Assert.assertEquals("Meatballs", storeItem.getName("A2"));
	}

	@Test
	public void get_type() {

		// Testing Get Store Item Type for B4
		storeItem.getFoodTypeMap();
		Assert.assertEquals("Beverage", storeItem.getFoodType("B4"));

		// Testing Get Store Item Type for B4
		storeItem.getFoodTypeMap();
		Assert.assertEquals("Appetizer", storeItem.getFoodType("A1"));

		// Testing Get Store Item Type for B4
		storeItem.getFoodTypeMap();
		Assert.assertEquals("Dessert", storeItem.getFoodType("D4"));

		// Testing Get Store Item Type for B4
		storeItem.getFoodTypeMap();
		Assert.assertEquals("Entree", storeItem.getFoodType("E4"));
	}

	@Test
	public void get_price() {

		// Testing Get Price From Item Code B4
		storeItem.getPriceMap();
		Assert.assertEquals(235, storeItem.getPrice("B4"));

		// Testing Get Price From Item Code A1
		storeItem.getPriceMap();
		Assert.assertEquals(350, storeItem.getPrice("A1"));

		// Testing Get Price From Item Code D3
		storeItem.getPriceMap();
		Assert.assertEquals(110, storeItem.getPrice("D3"));

		// Testing Get Price From Item Code E3
		storeItem.getPriceMap();
		Assert.assertEquals(1165, storeItem.getPrice("E3"));
	}

}