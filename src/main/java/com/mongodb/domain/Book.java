package com.mongodb.domain;
import com.mongodb.hibernate.annotations.ObjectIdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "books")
public class Book {
	@Id
	@ObjectIdGenerator
	@GeneratedValue
	ObjectId id;
	String title;
	Integer pages;
	List<Review> reviews;

	public void addReview(Review review) {
		if (this.reviews == null) {
			this.reviews = new ArrayList<>();
		}

		this.reviews.add(review);
	}

	public Book() {}
	public Book(String title, Integer pages) {
		this.title = title;
		this.pages = pages;
	}

	public Book(ObjectId id, String title, Integer pages) {
		this.id = id;
		this.title = title;
		this.pages = pages;
	}


	public ObjectId getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getPages() {
		return pages;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}


	@Override
	public String toString() {
		return "Book{id=%s, title='%s', totalPages='%s', reviews='%s'}"
				.formatted(id, title, pages, reviews);
	}
}

