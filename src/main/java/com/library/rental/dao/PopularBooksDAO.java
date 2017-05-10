package com.library.rental.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.library.rental.object.PopularBooks;
import com.library.rental.util.BookRentalConstants;
import com.library.rental.util.SessionUtil;

public class PopularBooksDAO {
	public void addBookRental(PopularBooks popularBooks) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(popularBooks); 
        tx.commit();
        session.close();
	}
	
	public int getUserCount(String isbn) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        
        Criteria criteria = session.createCriteria(PopularBooks.class);
        PopularBooks popularBooks = (PopularBooks) criteria.add(Restrictions.eq(BookRentalConstants.ISBN, isbn))
                                     .uniqueResult();
        
        tx.commit();
        session.close();
        return popularBooks != null ? popularBooks.getUserCount() : 0;
	}
}
