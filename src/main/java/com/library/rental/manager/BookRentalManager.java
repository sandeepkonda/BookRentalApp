package com.library.rental.manager;

import java.util.Date;
import java.util.Map;

import com.library.rental.client.BillingClient;
import com.library.rental.dao.BookRentalDAO;
import com.library.rental.object.BookRental;
import com.library.rental.util.Utils;

public class BookRentalManager {
	public static String INVENTORY_QUANTITY = "quantity";
	public static String INVENTORY_SKU = "sku";

	public String rentBook(BookRental bookRental) throws Exception {

		validateRentBookInput(bookRental);
		
		
		Map<String, Object> bookFromInv = Utils.getBookFromInventory(bookRental.getIsbn());
		int quantity = (int) bookFromInv.get(INVENTORY_QUANTITY);
		
		if(quantity < 1) {
			return "Book "+bookRental.getIsbn()+" is not available";
		}
		
		bookRental.setSku(INVENTORY_SKU);
		bookRental.setRentalDate(new Date());
		BookRentalDAO bookRentalDAO = new BookRentalDAO();
        bookRentalDAO.addBookRental(bookRental);
        
        Utils.decrementInventory(bookRental);
        
        PopularBooksManager popularBooksManager = new PopularBooksManager();
        
        popularBooksManager.updatePopularBooks(bookRental.getIsbn());
        
        return "Success";
        
	}

	private void validateRentBookInput(BookRental bookRental) throws Exception {
		if(bookRental.getIsbn() == null) {
			throw new Exception("Required input missing: isbn");
		}
		
		else if(bookRental.getUserId() == null) {
			throw new Exception("Required input missing: userId");
		}
	
	}

	public String returnBook(BookRental bookRental) throws Exception {
		
		validateReturnBookInput(bookRental);

		Date returnDate = new Date();
		bookRental.setReturnDate(returnDate);
		BookRentalDAO bookRentalDAO = new BookRentalDAO();
        bookRentalDAO.removeBookRental(bookRental);
		
        Utils.incrementInventory(bookRental);
        
        generateBill(bookRental, returnDate);
        
		return "Success";
	}
	
	private void generateBill(BookRental bookRental, Date returnDate) {
		BillingClient billingClient = new BillingClient();
		
		int numberOfDaysRented = 3;
		
		billingClient.generateBill(bookRental.getUserId(), bookRental.getIsbn(), numberOfDaysRented);
	}

	public void validateReturnBookInput(BookRental bookRental) throws Exception {
		if(bookRental.getIsbn() == null) {
			throw new Exception("Required input missing: isbn");
		}
		
		else if(bookRental.getSku() == null) {
			throw new Exception("Required input missing: sku");
		}
		
	}

	

}
