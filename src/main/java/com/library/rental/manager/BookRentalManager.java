package com.library.rental.manager;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.library.rental.client.BillingClient;
import com.library.rental.dao.BookRentalDAO;
import com.library.rental.object.BookRental;
import com.library.rental.util.BookRentalConstants;
import com.library.rental.util.Utils;

public class BookRentalManager {

	public String rentBook(BookRental bookRental) throws Exception {

		validateRentBookInput(bookRental);
		
		
		Map<String, Object> bookFromInv = Utils.getBookFromInventory(bookRental.getIsbn());
		int quantity = (int) bookFromInv.get(BookRentalConstants.QUANTITY);
		
		if(quantity < 1) {
			return "Book "+bookRental.getIsbn()+" is not available";
		}
		
		bookRental.setSku(BookRentalConstants.SKU);
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
			throw new Exception("Required input missing: "+BookRentalConstants.ISBN);
		}
		
		else if(bookRental.getUserId() == null) {
			throw new Exception("Required input missing: "+BookRentalConstants.USER_ID);
		}
	
	}

	public String returnBook(BookRental bookRental) throws Exception {
		
		validateReturnBookInput(bookRental);

		BookRentalDAO bookRentalDAO = new BookRentalDAO();
		
		BookRental bookRentalFromDB = bookRentalDAO.getBookRental(bookRental);
		if(bookRentalFromDB == null) {
			return "User "+bookRental.getUserId()+" has not rented out book "+bookRental.getIsbn();
		}
		
		bookRentalFromDB.setReturnDate(new Date());

		bookRentalDAO.removeBookRental(bookRentalFromDB);
		
        Utils.incrementInventory(bookRentalFromDB);
        
        generateBill(bookRentalFromDB);
        
		return "Success";
	}
	
	private void generateBill(BookRental bookRental) {
		BillingClient billingClient = new BillingClient();
		
		long diff = bookRental.getReturnDate().getTime() - bookRental.getRentalDate().getTime();
		long numberOfDaysRented = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		billingClient.generateBill(bookRental.getUserId(), bookRental.getIsbn(), numberOfDaysRented);
	}

	public void validateReturnBookInput(BookRental bookRental) throws Exception {
		if(bookRental.getIsbn() == null) {
			throw new Exception("Required input missing: "+BookRentalConstants.ISBN);
		}
		
		else if(bookRental.getSku() == null) {
			throw new Exception("Required input missing: "+BookRentalConstants.SKU);
		}
		
		else if(bookRental.getUserId() == null) {
			throw new Exception("Required input missing: "+BookRentalConstants.USER_ID);
		}
		
	}

	

}
