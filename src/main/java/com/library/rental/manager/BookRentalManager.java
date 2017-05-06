package com.library.rental.manager;

import com.library.rental.dao.BookRentalDAO;
import com.library.rental.object.BookRental;
import com.library.rental.object.PopularBooks;
import com.library.rental.util.Utils;

public class BookRentalManager {

	public String rentBook(BookRental bookRental) throws Exception {

		validateRentBookInput(bookRental);
		
		boolean isAvailable = Utils.checkInventoy(bookRental.getIsbn());
		
		if(!isAvailable) {
			return "Book "+bookRental.getIsbn()+" is not available";
		}
		
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
		
		BookRentalDAO bookRentalDAO = new BookRentalDAO();
        bookRentalDAO.removeBookRental(bookRental);
		
        Utils.incrementInventory(bookRental);
        
        generateBill(bookRental);
        
		return "Success";
	}
	
	private void generateBill(BookRental bookRental) {
		// TODO Call Billing Service
		
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
