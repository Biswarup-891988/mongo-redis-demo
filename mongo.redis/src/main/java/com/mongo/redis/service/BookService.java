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

@Service
public class BookService {

  private final BookDao bookDao;
  private final BookRepository bookRepository;

  public BookService(BookDao bookDao, BookRepository bookRepository) {
    this.bookDao = bookDao;
    this.bookRepository = bookRepository;
  }

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Map<String, Object> getPageCount() {
    return bookDao.countPage();
  }

  public Book getBooksByName(String name) {
    return bookRepository.findByTitle(name);
  }

  public Long totalBooks() {
    return bookRepository.count();
  }

  public List<Book> getBooksByCategory(String[] categories) {
    return bookDao.getBookByCategories(categories);
  }

  public Map<String, Object> getAllBooksByPage(int pageNo, int pageSizes, String sortBy) { 
    Sort sort = Sort.by(sortBy);
    Pageable pageRequest = PageRequest.of(pageNo, pageSizes, sort);
    Page<Book> page = bookRepository.findAll(pageRequest);
    Map<String, Object> response = new HashMap<>();
    response.put("Data", page.getContent());
    response.put("No of elements", page.getNumberOfElements());
    response.put("No of pages", page.getTotalPages());
    return response;
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
