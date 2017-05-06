package com.library.rental.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.library.rental.object.PopularBooks;
import com.library.rental.util.SessionUtil;

public class PopularBooksDAO {
	public void addBookRental(PopularBooks popularBooks) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        session.save(popularBooks); 
        tx.commit();
        session.close();
	}
	
	public int getUserCount(String isbn) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        PopularBooks popularBooks = (PopularBooks) session.get(PopularBooks.class, isbn); 
        tx.commit();
        session.close();
        return popularBooks.getUserCount();
	}
}
