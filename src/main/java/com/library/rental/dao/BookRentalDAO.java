package com.library.rental.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.library.rental.object.BookRental;
import com.library.rental.util.SessionUtil;

public class BookRentalDAO {

	public void addBookRental(BookRental bookRental) {
		bookRental.setRentalDate(new Date());
		bookRental.setSku("sku1");
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        session.save(bookRental); 
        tx.commit();
        session.close();
	}

	public void removeBookRental(BookRental bookRental) {
		// TODO Auto-generated method stub
		
	}

}
