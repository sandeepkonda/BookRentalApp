package com.library.rental.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "popular_books")
public class PopularBooks {
	@Id
    @GeneratedValue
    @Column(name = "id")
	private int id;
	
	@Column
	private String isbn;
	
	@Column(name = "user_count")
	private int userCount;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
}
