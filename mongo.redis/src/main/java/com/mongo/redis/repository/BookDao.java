package com.mongo.redis.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.mongo.redis.model.Book;

@Repository
public class BookDao {

  @Autowired
  MongoTemplate mongotemplate;

  public Map<String,Object> countPage() {
    Aggregation aggregation =
        Aggregation.newAggregation(Aggregation.group().sum("$pageCount").as("count"));
    return mongotemplate.aggregate(aggregation, "books", Book.class).getRawResults();
  }

  public List<Book> getBookByCategories(String[] categories){  
    Query query = new Query();
    query.addCriteria(Criteria.where("categories").in(Arrays.asList(categories)));
    return mongotemplate.find(query, Book.class, "books");   
  }
  
  
  
  
}
