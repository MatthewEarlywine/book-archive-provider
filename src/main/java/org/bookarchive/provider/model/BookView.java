package org.bookarchive.provider.model;

import javax.persistence.Entity;

import org.bookarchive.provider.entity.Book;
import org.springframework.beans.BeanUtils;

public class BookView implements Book {

	private Long id;

	private String title;

	private String series;

	private String author;

	private String illustrator;

	private String genre;

	BookView() {}
	
	BookView(Book book){
		BeanUtils.copyProperties(book, this, BookView.class);
	}
	
	public static BookView convert(Book book) {
		if (book == null) { 
			return null;
		}
		if (book instanceof BookView) {
			return (BookView) book;
		}
		return new BookView(book);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIllustrator() {
		return illustrator;
	}

	public void setIllustrator(String illustrator) {
		this.illustrator = illustrator;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BookView))
			return false;
		BookView other = (BookView) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", series=" + series + ", author=" + author + ", illustrator="
				+ illustrator + ", genre=" + genre + "]";
	}

}
