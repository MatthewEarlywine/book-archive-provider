package org.bookarchive.provider.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "BOOK")
public class BookImpl implements Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "SERIES")
	private String series;

	@Column(name = "AUTHOR", nullable = false)
	private String author;

	@Column(name = "ILLUSTRATOR")
	private String illustrator;

	@Column(name = "GENRE")
	private String genre;

	BookImpl() {}
	
	BookImpl(Book book){
		BeanUtils.copyProperties(book, this, BookImpl.class);
	}
	
	public static BookImpl convert(Book book) {
		if (book == null) { 
			return null;
		}
		if (book instanceof BookImpl) {
			return (BookImpl) book;
		}
		return new BookImpl(book);
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
		if (!(obj instanceof BookImpl))
			return false;
		BookImpl other = (BookImpl) obj;
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
