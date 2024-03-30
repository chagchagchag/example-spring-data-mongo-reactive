package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

import java.math.BigDecimal;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookDocumentRepository extends ReactiveSortingRepository<BookDocument, ObjectId> {
  Flux<BookDocument> findAllByName(Publisher<String> name);

  Mono<BookNameDocument> findFirstByName(String name);

  Mono<Long> deleteByName(String name);

  Mono<BookDocument> deleteByPriceGreaterThan(BigDecimal price);

  @Query("{'name': ?0}")
  Flux<BookDocument> findAllByName(String name);

  @Aggregation(pipeline = {
      "{ $match: {name: ?0} }",
      "{ $group: {_id:  '$name', count:  {$sum:  1}}}"
  })
  Mono<BookCountAggregate> aggregateGroupByName(String name);

}
