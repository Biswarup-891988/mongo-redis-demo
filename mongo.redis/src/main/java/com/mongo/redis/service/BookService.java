package com.mongo.redis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.mongo.redis.model.Book;
import com.mongo.redis.repository.BookDao;
import com.mongo.redis.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {

  private final BookDao bookDao;
  private final BookRepository bookRepository;

  public BookService(BookDao bookDao, BookRepository bookRepository) {
    this.bookDao = bookDao;
    this.bookRepository = bookRepository;
  }

  public List<Book> getAllBooks() {
    log.info("BookService-getAllBooks");
    return bookRepository.findAll();
  }

  public List<Book> getBooksByCategory(String[] categories) {
    log.info("BookService-getBooksByCategory");
    return bookDao.getBookByCategories(categories);
  }

  public Map<String, Object> getAllBooksByPage(int pageNo, int pageSizes, String sortBy) {
    log.info("BookService-getAllBooksByPage");
    Sort sort = Sort.by(sortBy);
    Pageable pageRequest = PageRequest.of(pageNo, pageSizes, sort);
    Page<Book> page = bookRepository.findAll(pageRequest);
    Map<String, Object> response = new HashMap<>();
    response.put("Data", page.getContent());
    response.put("No of elements", page.getNumberOfElements());
    response.put("No of pages", page.getTotalPages());
    return response;
  }

  public Book getBooksByName(String name) {
    log.info("BookService-getBooksByName");
    return bookRepository.findByTitle(name);
  }

  public Map<String, Object> getPageCount() {
    return bookDao.countPage();
  }

  public Long totalBooks() {
    return bookRepository.count();
  }

  public void deleteBook(String name) {
    Book book = bookRepository.findByTitle(name);
    bookRepository.delete(book);
  }

  public Book updateBook(Book book) {
    return bookRepository.save(book);
  }

  public Book addBook(Book book) {
    return bookRepository.insert(book);
  }

}
