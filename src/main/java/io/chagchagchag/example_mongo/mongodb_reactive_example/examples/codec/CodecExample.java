package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.codec;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import io.chagchagchag.example_mongo.mongodb_reactive_example.examples.codec.valueobject.Person;
import lombok.extern.slf4j.Slf4j;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class CodecExample {
  public static void main(String[] args) {
    CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
        MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(new CustomValueCodecProvider()),
        CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
    );

    var connectionString = new ConnectionString(
        "mongodb+srv://[계정명]:[비밀번호]@[Mongo Cloud 접속 주소]/"
    );

    MongoClient mongoClient = MongoClients.create(
        MongoClientSettings.builder()
            .codecRegistry(codecRegistry)
            .applyConnectionString(connectionString)
            .build()
    );

    // Mongodb 데이터베이스 접속, 컬렉션 조회
    MongoDatabase database = mongoClient.getDatabase("helloworld");
    MongoCollection<Person> collection = database.getCollection("person", Person.class);

    // 도큐먼트 조회
    collection.find().first()
            .subscribe(newSubscriber(1));
  }

  public static Subscriber<Person> newSubscriber(int requestSize){
    return new Subscriber<Person>() {
      @Override
      public void onSubscribe(Subscription subscription) {
        subscription.request(requestSize);
      }

      @Override
      public void onNext(Person item) {
        log.info("item _id = " + item.getId());
        log.info("item name = " + item.getName());
        log.info("item salary = " + item.getSalary());
      }

      @Override
      public void onError(Throwable throwable) {
        throwable.printStackTrace();
      }

      @Override
      public void onComplete() {
        System.out.println("complete");
      }
    };
  }
}
