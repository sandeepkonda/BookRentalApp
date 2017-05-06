package com.library.rental.util;

import com.library.rental.client.InventoryClient;
import com.library.rental.object.BookRental;

public class Utils {
	public static boolean checkInventoy(String isbn) {
		InventoryClient inventoryClient = new InventoryClient();
		
		inventoryClient.checkInventory(isbn);
		
		return true;
	}


	public static void decrementInventory(BookRental bookRental) {
		InventoryClient inventoryClient = new InventoryClient();
		
		inventoryClient.decrementInventory(bookRental.getIsbn());
		
	}
	
	public static void incrementInventory(BookRental bookRental) {
		// TODO Call inventory service
		//
		
	}
}
