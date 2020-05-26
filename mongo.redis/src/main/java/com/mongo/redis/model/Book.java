package com.mongo.redis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "books")
public class Book implements Serializable {

  private static final long serialVersionUID = -1006562029888045860L;
  @Id
  private String _id;
  private String title;
  private String isbn;
  public String pageCount;
  private Date publishedDate;
  private String thumbnailUrl;
  private String longDescription;
  private String status;
  private List<String> authors;
  private List<String> categories;

}
