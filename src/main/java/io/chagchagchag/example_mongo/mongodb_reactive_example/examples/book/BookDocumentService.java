package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookDocumentService {
  private final BookDocumentFactory documentFactory;
  private final BookDocumentTransactionRepository bookDocumentTransactionRepository;

  @Transactional
  public Flux<BookDocument> insertNewBook(String name, BigDecimal price){
    BookDocument book = documentFactory.newBookDocument(
        name, price, LocalDateTime.now()
    );

    return bookDocumentTransactionRepository.insert(book)
        .flatMap(bookDocument -> {
          BookDocument forSale = documentFactory.withSaleStatus(bookDocument, SaleStatus.FOR_SALE);
          return bookDocumentTransactionRepository.save(forSale);
        })
        .thenMany(bookDocumentTransactionRepository.findAll());
  }

}
