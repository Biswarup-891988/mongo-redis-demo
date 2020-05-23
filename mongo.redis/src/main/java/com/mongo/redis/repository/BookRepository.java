package com.mongo.redis.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.mongo.redis.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer>{

  public Book findByTitle(String name);

}
