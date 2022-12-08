package org.bookarchive.provider.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bookarchive.provider.entity.Book;
import org.bookarchive.provider.entity.BookImpl;
import org.bookarchive.provider.model.BookView;
import org.bookarchive.provider.service.ListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/favoritebooks")
@Configuration
@ComponentScan("org.bookarchive")
public class RestListController {

	Logger logger = LoggerFactory.getLogger(RestListController.class);

	@Autowired
	private ListService bookService;

	
	ModelAndView mv = new ModelAndView("bookList");

	@GetMapping
	public ModelAndView getBookListHome() {
			
		return mv;
	}

	@GetMapping(value = "/getAllBooks")
	public ResponseEntity<?> getAllBooks(){
		List<BookView> bookList = bookService.findAllBooks()
								  .stream().map(BookView :: convert)
								  .collect(Collectors.toList());
		return new ResponseEntity<List<BookView>>(bookList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkBook", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> checkBook(BookView book){
		System.out.println("Checking if book exists REST.");
		System.out.println(book.getTitle() + " 1");
		Boolean answer = bookService.doesBookExist(book);
		return new ResponseEntity<Boolean>(answer, HttpStatus.OK);
	}
	
	@PostMapping(value = "/saveBook")
	public ResponseEntity<?> saveBook(@RequestBody BookView book){
        if (bookService.doesBookExist(book)) {
            logger.debug("A book with title " + book.getTitle() + " authored by " + book.getAuthor() + " is already listed");
            System.out.println("A book with title " + book.getTitle() + " authored by " + book.getAuthor() + " is already listed");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		bookService.saveBook(book);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateBook(@PathVariable("id") Long id, @RequestBody BookView book) {
		logger.debug("Updating Book " + id);

//		BookImpl currentBook = bookService.findById(id);
//
//		if (currentBook == null) {
//			logger.debug("Book with id " + id + " not found");
//			return new ResponseEntity<BookImpl>(HttpStatus.NO_CONTENT);
//		}
//
//		currentBook.setTitle(book.getTitle());
//		currentBook.setSeries(book.getSeries());
//		currentBook.setAuthor(book.getAuthor());
//		currentBook.setIllustrator(book.getIllustrator());
//		currentBook.setGenre(book.getGenre());

		bookService.updateBook(book);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
		logger.debug("Fetching & Deleting Book with id " + id);

//		BookImpl book = bookService.findById(id);
//		if (book == null) {
//			logger.debug("Unable to delete. Book with id " + id + " not found");
//			return new ResponseEntity<BookImpl>(HttpStatus.NO_CONTENT);
//		}

		bookService.deleteBookById(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}