package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "book")
public class BookNameDocument {
  private String name;
}
