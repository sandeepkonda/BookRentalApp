package com.library.rental.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.library.rental.object.BookRental;
import com.library.rental.util.BookRentalConstants;
import com.library.rental.util.SessionUtil;

public class BookRentalDAO {

	public void addBookRental(BookRental bookRental) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        session.save(bookRental); 
        tx.commit();
        session.close();
	}

	public void removeBookRental(BookRental bookRental) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        session.update(bookRental); 
        tx.commit();
        session.close();
		
	}

	public BookRental getBookRental(BookRental bookRental) {
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        
        Criteria criteria = session.createCriteria(BookRental.class);
        BookRental bookRentalFromDB = (BookRental) criteria
        		.add(Restrictions.eq(BookRentalConstants.ISBN, bookRental.getIsbn()))
        		.add(Restrictions.eq(BookRentalConstants.SKU, bookRental.getSku()))
        		.add(Restrictions.eq(BookRentalConstants.USER_ID, bookRental.getUserId()))
        		.add(Restrictions.isNull(BookRentalConstants.RETURN_DATE))
                .uniqueResult();
        
        tx.commit();
        session.close();
        return bookRentalFromDB;
	}

}
