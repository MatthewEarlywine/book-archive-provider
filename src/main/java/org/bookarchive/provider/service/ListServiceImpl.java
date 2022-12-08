package org.bookarchive.provider.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.bookarchive.provider.entity.Book;
import org.bookarchive.provider.entity.BookImpl;
import org.bookarchive.provider.repository.BookDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ListServiceImpl implements ListService {

	@Autowired 				
	private BookDao bookRepo; 	
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findAllBooks() {
		Session s = sessionFactory.getCurrentSession();		
		CriteriaBuilder cb = s.getCriteriaBuilder();		
		CriteriaQuery<BookImpl> cq = cb.createQuery(BookImpl.class);		
		Root<BookImpl> root = cq.from(BookImpl.class);		
		cq.select(root);		
		Query query = s.createQuery(cq);		
		return query.getResultList();
	}

	@SuppressWarnings("deprecation")
	public Book findById(Long id) {
		return bookRepo.getReferenceById(id);
	}
	
	@Override
	public Book findByTitle(String title) {
		return bookRepo.findByTitle(title);
	}
	
	@Override
	public Book saveBook(Book book) {
		return bookRepo.save(BookImpl.convert(book));
	}

	public Book updateBook(Book book) {
		BookImpl updatedBook = BookImpl.convert(book);
		return bookRepo.save(updatedBook);
	}

	public void deleteBookById(Long id) {
		bookRepo.deleteById(id);
	}

	@Override
	public boolean doesBookExist(Book book) {
		System.out.println(book.getTitle());
		Query query = sessionFactory.openSession().createQuery("FROM Book b WHERE b.title = :title and b.author = :author and b.illustrator = :illustrator");
		query.setParameter("title", book.getTitle());
		query.setParameter("author", book.getAuthor());
		query.setParameter("illustrator", book.getIllustrator());
		System.out.println(book.getTitle());
		List<?> pList = query.getResultList();
		System.out.println(pList.size());
		if (!pList.isEmpty()) {
			return true;
		}
		return false;
	
	}
}
