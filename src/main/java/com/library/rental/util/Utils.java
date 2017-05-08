package com.library.rental.util;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.library.rental.client.InventoryClient;
import com.library.rental.object.BookRental;

public class Utils {
	public static Map<String, Object> getBookFromInventory(String isbn) {
		InventoryClient inventoryClient = new InventoryClient();
		
		String inventory = inventoryClient.checkInventory(isbn);
		Map<String, Object> map = null;
		int quantity = 0;
		try {
			map = new ObjectMapper().readValue(inventory, new TypeReference<Map<String, Object>>() {
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return map;
	}


	public static void decrementInventory(BookRental bookRental) {
		InventoryClient inventoryClient = new InventoryClient();
		
		inventoryClient.decrementInventory(bookRental.getIsbn());
		
	}
	
	public static void incrementInventory(BookRental bookRental) {
		InventoryClient inventoryClient = new InventoryClient();
		
		inventoryClient.incrementInventory(bookRental.getIsbn());
	}
}
