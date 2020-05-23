package com.mongo.redis.restController;

import java.util.List;
import java.util.Map;
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
public class BookRestController {

  private final BookService bookService;

  public BookRestController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/book")
  public Book getBooksByName(@RequestParam String bookName) {
    return bookService.getBooksByName(bookName);
  }
  
  @GetMapping("/allBooks")
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/totalBooks")
  public Long totalBooks() {
    return bookService.totalBooks();
  }

  @GetMapping("/pageCount")
  public Map<String,Object> getBooksByPageCount() {
    return bookService.getPageCount();
  }

  @GetMapping("/category")
  public List<Book> getBooksByCategory(@RequestParam(value = "category") String[] categories){
    return bookService.getBooksByCategory(categories);
  }
  
  @GetMapping("/page")
  public Map<String, Object> getAllBooksByPage(
      @RequestParam int pageNo,
      @RequestParam int pageSizes,
      @RequestParam String sortBy
      ){
    return bookService.getAllBooksByPage(pageNo,pageSizes,sortBy);
  }
  
  @PostMapping("/addBook")
  public Book addBook(@RequestBody Book book) {
    return bookService.addBook(book);
  }

  @PutMapping("/updateBook")
  public Book updateBook(@RequestBody Book book) {
    return bookService.updateBook(book);
  }

  @DeleteMapping("/deleteBook")
  public void deleteBook(@RequestParam("name") String name) {
    bookService.deleteBook(name);
  }

}
