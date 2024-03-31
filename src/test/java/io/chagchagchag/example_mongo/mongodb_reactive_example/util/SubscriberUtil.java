package io.chagchagchag.example_mongo.mongodb_reactive_example.util;

import io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.BookDocument;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.BaseSubscriber;

public class SubscriberUtil {
  private static final Logger log = LoggerFactory.getLogger(SubscriberUtil.class);

  public static BaseSubscriber<BookDocument> newBaseSubscriber(int requestSize){
    return new BaseSubscriber<BookDocument>() {
      private Subscription subscription;
      @Override
      protected void hookOnSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(requestSize);
      }

      @Override
      protected void hookOnNext(BookDocument value) {
        log.info("item _id = " + value.getId());
        log.info("item name = " + value.getName());
        log.info("item price = " + value.getPrice());
        log.info("item saleStatus = " + value.getSaleStatus());
        subscription.request(requestSize);
      }

      @Override
      protected void hookOnComplete() {
        log.info("complete");
      }
    };
  }

  public static Subscriber<BookDocument> newSubscriber(int requestSize){
    return new Subscriber<BookDocument>() {
      private Subscription subscription;
      @Override
      public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(requestSize);
      }

      @Override
      public void onNext(BookDocument item) {
        log.info("item _id = " + item.getId());
        log.info("item name = " + item.getName());
        log.info("item price = " + item.getPrice());
        log.info("item saleStatus = " + item.getSaleStatus());
//        subscription.request(requestSize);
      }

      @Override
      public void onError(Throwable throwable) {
        throwable.printStackTrace();
        log.info("throwable message == " + throwable.getMessage());
      }

      @Override
      public void onComplete() {
        log.info("complete");
      }
    };
  }

}
