package com.techelevator.store;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartItemTest {
	private CartItem cartItem;

	@Before
	public void setup() {
		cartItem = new CartItem();
		cartItem.setStartingInventoryMap();
	}

	@Test
	public void get_starting_string_of_zero_items() {

		// Test Initial Start with B2
		Assert.assertEquals(0, cartItem.getInitialAmountOfItems("B2"));

		// Testing Initial Start with A2
		Assert.assertEquals(0, cartItem.getInitialAmountOfItems("A2"));

		// Testing Initial Start with E2
		Assert.assertEquals(0, cartItem.getInitialAmountOfItems("E2"));

		// Testing Initial Start with D2
		Assert.assertEquals(0, cartItem.getInitialAmountOfItems("D2"));
	}

	@Test
	public void add_item_test() {

		// Testing Item B4
		cartItem.addToCart("B4", 30);
		Assert.assertEquals(30, cartItem.getAmountOfItems("B4"));

		// Testing Item A3
		cartItem.addToCart("A3", 20);
		Assert.assertEquals(20, cartItem.getAmountOfItems("A3"));

		// Testing Item D1
		cartItem.addToCart("D1", 1);
		Assert.assertEquals(1, cartItem.getAmountOfItems("D1"));

		// Testing Item E2
		cartItem.addToCart("E2", 13);
		Assert.assertEquals(13, cartItem.getAmountOfItems("E2"));

	}

	@Test
	public void get_name() {

		// Testing Get Name From Code B4
		cartItem.getNameMap();
		Assert.assertEquals("Sparkling Water", cartItem.getName("B4"));

		// Testing Get Name From Code E1
		cartItem.getNameMap();
		Assert.assertEquals("Baked Chicken", cartItem.getName("E1"));

		// Testing Get Name From Code D2
		cartItem.getNameMap();
		Assert.assertEquals("Cake", cartItem.getName("D2"));

		// Testing Get Name From Code A2
		cartItem.getNameMap();
		Assert.assertEquals("Meatballs", cartItem.getName("A2"));
	}

	@Test
	public void get_type() {

		// Testing Get Type From Code B4
		cartItem.getFoodTypeMap();
		Assert.assertEquals("Beverage", cartItem.getFoodType("B4"));

		// Testing Get Type From Code A1
		cartItem.getFoodTypeMap();
		Assert.assertEquals("Appetizer", cartItem.getFoodType("A1"));

		// Testing Get Type From Code D3
		cartItem.getFoodTypeMap();
		Assert.assertEquals("Dessert", cartItem.getFoodType("D3"));

		// Testing Get Type From Code E1
		cartItem.getFoodTypeMap();
		Assert.assertEquals("Entree", cartItem.getFoodType("E1"));
	}

	@Test
	public void get_price() {

		// Testing Get Price From Item Code B4
		cartItem.getPriceMap();
		Assert.assertEquals(235, cartItem.getPrice("B4"));

		// Testing Get Price From Item Code A1
		cartItem.getPriceMap();
		Assert.assertEquals(350, cartItem.getPrice("A1"));

		// Testing Get Price From Item Code D3
		cartItem.getPriceMap();
		Assert.assertEquals(110, cartItem.getPrice("D3"));

		// Testing Get Price From Item Code E3
		cartItem.getPriceMap();
		Assert.assertEquals(1165, cartItem.getPrice("E3"));

	}
}
