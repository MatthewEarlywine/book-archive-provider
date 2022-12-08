package org.bookarchive.provider.entity;

import javax.persistence.Entity;

public interface Book {
	
	public Long getId();

	public void setId(Long id);

	public String getTitle();

	public void setTitle(String title);

	public String getSeries();

	public void setSeries(String series);

	public String getAuthor();

	public void setAuthor(String author);

	public String getIllustrator();

	public void setIllustrator(String illustrator);

	public String getGenre();

	public void setGenre(String genre);
}
