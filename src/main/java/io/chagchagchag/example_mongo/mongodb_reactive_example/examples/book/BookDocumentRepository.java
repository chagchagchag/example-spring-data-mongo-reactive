package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

import java.math.BigDecimal;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookDocumentRepository extends ReactiveSortingRepository<BookDocument, ObjectId> {
  Flux<BookDocument> findAllByName(Publisher<String> name);

  Flux<BookDocument> deleteBySaleStatusAndPriceGreaterThan(SaleStatus saleStatus, BigDecimal price);

  Mono<BookDocument> deleteBySaleStatus(SaleStatus saleStatus);

  Mono<BookDocument> findFirstByNameOrderByPriceDesc(String name);

  Mono<BookNameDocument> findFirstByNameOrderByPublishedAtDesc(String name);

  @Query("{'name': ?0}")
  Flux<BookDocument> findAllByName(String name);

  @Query("{_id:  $0}")
  @Update(value = "{$set:  {name: ?1}}")
  Mono<Integer> updateNameById(String id, String name);
}
