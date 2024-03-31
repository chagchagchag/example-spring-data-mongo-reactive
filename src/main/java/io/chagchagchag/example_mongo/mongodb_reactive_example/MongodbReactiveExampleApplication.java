package io.chagchagchag.example_mongo.mongodb_reactive_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories(
		basePackages = {
				"io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book",
		},
		reactiveMongoTemplateRef = "helloworldReactiveMongoTemplate"
)
@SpringBootApplication
public class MongodbReactiveExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbReactiveExampleApplication.class, args);
	}

}
