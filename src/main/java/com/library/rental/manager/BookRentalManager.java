package com.library.rental.manager;

import com.library.rental.dao.BookRentalDAO;
import com.library.rental.object.BookRental;
import com.library.rental.object.PopularBooks;

public class BookRentalManager {

	public void rentBook(BookRental bookRental) {

		BookRentalDAO bookRentalDAO = new BookRentalDAO();
        bookRentalDAO.addBookRental(bookRental);
        
        PopularBooksManager popularBooksManager = new PopularBooksManager();
        
        PopularBooks popularBooks = new PopularBooks();
        popularBooks.setIsbn(bookRental.getIsbn());
        popularBooksManager.updatePopularBooks(popularBooks);
        
	}

}
