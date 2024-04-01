package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class BookDocumentFactory {
  public BookDocument of(ObjectId objectId, String name, BigDecimal price, LocalDateTime publishedAt, SaleStatus saleStatus){
    return new BookDocument(
        objectId, name, price, publishedAt, saleStatus
    );
  }

  public BookDocument newBookDocument(
      String name, BigDecimal price, LocalDateTime publishedAt
  ){
    return of(
        null, name, price, publishedAt, SaleStatus.WAITING_FOR_SALE
    );
  }

  public BookDocument withSaleStatus(
      BookDocument bookDocument, SaleStatus saleStatus
  ){
    return of(
        bookDocument.getId(), bookDocument.getName(), bookDocument.getPrice(), bookDocument.getPublishedAt(), saleStatus
    );
  }
}
