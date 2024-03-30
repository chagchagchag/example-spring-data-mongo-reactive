package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

public record BookCountAggregate (
    double total,
    double for_sale,
    double sold_out
){

}
