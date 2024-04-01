package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookDocumentTransactionRepository extends ReactiveMongoRepository<BookDocument, ObjectId> {
}
