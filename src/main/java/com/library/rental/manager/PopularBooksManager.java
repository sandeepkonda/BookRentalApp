package com.library.rental.manager;

import com.library.rental.dao.PopularBooksDAO;
import com.library.rental.object.PopularBooks;

public class PopularBooksManager {
	public void updatePopularBooks(PopularBooks popularBooks) {

		PopularBooksDAO popularBooksDAO = new PopularBooksDAO();
		int userCount = popularBooksDAO.getUserCount(popularBooks.getIsbn());
		popularBooks.setUserCount(userCount + 1);
		popularBooksDAO.addBookRental(popularBooks);

	}
	
	
}
