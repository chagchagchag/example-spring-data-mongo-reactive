package io.chagchagchag.example_mongo.mongodb_reactive_example.examples;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MongoClientExample2 {
  public static void main(String[] args) {
    var connection = new ConnectionString(
        "mongodb+srv://[계정명]:[비밀번호]@[Mongo Cloud 접속 주소]/"
    );

    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(connection)
        .build();

    try(MongoClient mongoClient = MongoClients.create(mongoClientSettings)){
      var database = mongoClient.getDatabase("helloworld");
      log.info("database : {}", database.getName());

      var collection = database.getCollection("book");
      log.info("collection : {}", collection.getNamespace().getCollectionName());

      log.info("Finish. Bye Bye~!!");
    }

  }
}
