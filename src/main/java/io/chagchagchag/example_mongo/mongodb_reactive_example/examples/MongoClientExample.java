package io.chagchagchag.example_mongo.mongodb_reactive_example.examples;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

@Slf4j
public class MongoClientExample {
  public static void main(String[] args) {
    MongoClient mongoClient = MongoClients.create(
        "mongodb+srv://[계정명]:[비밀번호]@[Mongo Cloud 접속 주소]/"
    );

    MongoDatabase helloworld = mongoClient.getDatabase("helloworld");
    log.info("database == {} ", helloworld.getName());
    MongoCollection<Document> book = helloworld.getCollection("book");
    log.info("collection = {} ", book.getNamespace().getCollectionName());

    log.info("Bye Bye. Close ~!!!");
    mongoClient.close();
  }
}
