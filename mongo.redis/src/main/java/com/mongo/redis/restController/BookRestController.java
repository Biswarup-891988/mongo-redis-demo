package com.mongo.redis.restController;

import java.util.List;
import java.util.Map;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mongo.redis.model.Book;
import com.mongo.redis.service.BookService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/books")
@CacheConfig(cacheNames = "redisMongoCache")
public class BookRestController {

  private final BookService bookService;

  public BookRestController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/book")
  @Cacheable(value = "books", key = "#title")
  public Book getBooksByName(@RequestParam String title) {
    log.info("BookRestController-getBooksByName");
    return bookService.getBooksByName(title);
  }
  
  @PutMapping("/updateBook")
  @CachePut(value = "books", key = "#title")
  public Book updateBook(@RequestBody Book book, @RequestParam("title") String title) {
    log.info("BookRestController-updateBook");
    return bookService.updateBook(book);
  }

  @DeleteMapping("/deleteBook")
  @CacheEvict(value = "books", key = "#title")
  public void deleteBook(@RequestParam("title") String title) {
    log.info("BookRestController-deleteBook");
    bookService.deleteBook(title);
  }

  @GetMapping("/allBooks")
  @Cacheable(value = "books")
  public List<Book> getAllBooks() {
    log.info("BookRestController-getAllBooks");
    return bookService.getAllBooks();
  }

  @GetMapping("/category")
  @Cacheable(value = "books", key = "#categories")
  public List<Book> getBooksByCategory(@RequestParam(value = "category") String[] categories) {
    log.info("BookRestController-getBooksByCategory");
    return bookService.getBooksByCategory(categories);
  }

  @PostMapping("/addBook")
  public Book addBook(@RequestBody Book book) {
    log.info("BookRestController-addBook");
    return bookService.addBook(book);
  }

  @GetMapping("/page")
  public Map<String, Object> getAllBooksByPage(@RequestParam int pageNo,
      @RequestParam int pageSizes, @RequestParam String sortBy) {
    log.info("BookRestController-getAllBooksByPage");
    return bookService.getAllBooksByPage(pageNo, pageSizes, sortBy);
  }

  @GetMapping("/totalBooks")
  public Long totalBooks() {
    return bookService.totalBooks();
  }

  @GetMapping("/pageCount")
  public Map<String, Object> getBooksByPageCount() {
    return bookService.getPageCount();
  }

  @GetMapping("/evictAllCache")
  @CacheEvict(allEntries = true, value = "books")
  public String deleteAllCache() {
    log.info("Deleting Cache");
    return "Deleted Full Cache";
  }

}
