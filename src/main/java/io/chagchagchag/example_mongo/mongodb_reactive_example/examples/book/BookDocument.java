package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "book")
public class BookDocument {
  private ObjectId id;
  private String name;
  private BigDecimal price;
  private LocalDateTime publishedAt;
  private SaleStatus saleStatus;
}
