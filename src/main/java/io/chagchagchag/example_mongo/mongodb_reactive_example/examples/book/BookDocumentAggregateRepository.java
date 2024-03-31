package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

import java.math.BigDecimal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface BookDocumentAggregateRepository extends ReactiveMongoRepository<BookDocument, ObjectId> {
  @Aggregation(pipeline = {
      "{ $match: {price: {$gte: ?0, $lte:  ?1}} }",
      "{ $group: {_id:  {saleStatus: '$saleStatus'}, cnt: {$count: {}}}}",
      "{ $project:  {status: '$_id.saleStatus', cnt: '$cnt'}}"
  })
  Flux<BookCountAggregate> aggregateGroupByStatusAndCounting(BigDecimal lowerLimit, BigDecimal upperLimit);
}
