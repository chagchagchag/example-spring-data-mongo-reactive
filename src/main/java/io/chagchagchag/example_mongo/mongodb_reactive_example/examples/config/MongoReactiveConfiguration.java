package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import java.math.BigDecimal;
import java.util.Arrays;
import org.bson.json.StrictJsonWriter;
import org.bson.types.Decimal128;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoReactiveConfiguration {
  @Value("${spring.data.mongodb.uri}")
  private String mongoUri;

  @Bean
  public MongoClient reactiveMongoClient(){
    return MongoClients.create(mongoUri);
  }

  @Bean
  public ReactiveMongoTransactionManager transactionManager(
      ReactiveMongoDatabaseFactory dbFactory
  ){
    return new ReactiveMongoTransactionManager(dbFactory);
  }

  @Bean(name = "helloworldReactiveMongoDatabaseFactory")
  public SimpleReactiveMongoDatabaseFactory helloworldReactiveMongoDatabaseFactory(
      MongoProperties mongoProperties,
      MongoClient mongoClient
  ){
    // 이렇게 할 수도 있지만, 하나의 프로젝트에서 여러 몽고 database 를 사용할 수 있으므로 비활성화
//    String database = mongoProperties.getMongoClientDatabase();
    final String database = "helloworld";
    return new SimpleReactiveMongoDatabaseFactory(mongoClient, database);
  }

  @Bean(name = "helloworldReactiveMongoTemplate")
  public ReactiveMongoTemplate helloworldReactiveMongoTemplate(
      ReactiveMongoDatabaseFactory helloworldReactiveMongoDatabaseFactory,
      MongoConverter mongoConverter
  ){
    return new ReactiveMongoTemplate(helloworldReactiveMongoDatabaseFactory, mongoConverter);
  }

  @Bean
  public MongoCustomConversions mongoCustomConversions(){
    return new MongoCustomConversions(
        Arrays.asList(
            new BigDecimalToDecimal128Converter(),
            new Decimal128ToBigDecimalConverter()
        )
    );
  }

}
