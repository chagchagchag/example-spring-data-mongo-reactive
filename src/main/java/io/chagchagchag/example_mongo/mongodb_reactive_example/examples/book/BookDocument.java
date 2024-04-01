package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Document(collection = "book")
public class BookDocument {
  private ObjectId id;
  private String name;
  @Field(targetType = FieldType.DECIMAL128)
  private BigDecimal price;
  private LocalDateTime publishedAt;
  private SaleStatus saleStatus;

  public BookDocument(
    ObjectId id,
    String name,
    BigDecimal price,
    LocalDateTime publishedAt,
    SaleStatus saleStatus
  ){
    this.id = id;
    this.name = name;
    this.price = price;
    this.publishedAt = publishedAt;
    this.saleStatus = saleStatus;
  }

}
