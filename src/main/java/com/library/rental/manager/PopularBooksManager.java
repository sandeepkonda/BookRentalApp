package com.library.rental.manager;

import java.util.ArrayList;
import java.util.List;

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

	public List<String> getPopularBook() {
		PopularBooksDAO popularBooksDAO = new PopularBooksDAO();
		List<PopularBooks> popularBooksList = popularBooksDAO.getPopularBook();
		
		List<String> popularBooksIsbn = new ArrayList<String>();
		for(PopularBooks popularBooks : popularBooksList){
			popularBooksIsbn.add(popularBooks.getIsbn());
		}
		 
		return popularBooksIsbn;
	}
	
	
}
