package com.library.rental.manager;

import com.library.rental.dao.PopularBooksDAO;
import com.library.rental.object.PopularBooks;

public class PopularBooksManager {
	public void updatePopularBooks(String isbn) {

		PopularBooks popularBooks = new PopularBooks();
        popularBooks.setIsbn(isbn);
        
		PopularBooksDAO popularBooksDAO = new PopularBooksDAO();
		int userCount = popularBooksDAO.getUserCount(isbn);
		popularBooks.setUserCount(userCount + 1);
		popularBooksDAO.addBookRental(popularBooks);

	}
	
	
}
