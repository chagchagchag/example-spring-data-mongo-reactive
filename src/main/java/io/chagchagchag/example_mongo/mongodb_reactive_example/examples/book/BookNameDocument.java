package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
public class BookNameDocument {
  private String name;
}
