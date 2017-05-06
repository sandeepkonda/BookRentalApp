package com.library.rental.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "book_rental")
public class BookRental {
	@Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
	
	@Column
	private String sku;
	@Column
	private String isbn;
	@Column
	private String userId;
	
	@Column(name = "rental_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date rentalDate;
	
	@Column(name = "return_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	
	

}
