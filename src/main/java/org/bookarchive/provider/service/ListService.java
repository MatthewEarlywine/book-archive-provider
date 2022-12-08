package org.bookarchive.provider.service;

import java.util.List;

import org.bookarchive.provider.entity.Book;

public interface ListService {

	public Book findById(Long id);

	public Book saveBook(Book book);

	public Book updateBook(Book book);

	public void deleteBookById(Long id);

	public List<Book> findAllBooks();

	public boolean doesBookExist(Book book);

	public Book findByTitle(String title);

}
