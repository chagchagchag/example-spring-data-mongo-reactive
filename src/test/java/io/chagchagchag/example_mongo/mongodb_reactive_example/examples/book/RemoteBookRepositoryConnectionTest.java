package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@SpringBootTest
public class RemoteBookRepositoryConnectionTest {
  static Logger log = LoggerFactory.getLogger(RemoteBookRepositoryConnectionTest.class);

  @Autowired
  private BookDocumentRepository bookDocumentRepository;

  @Test
  public void JUST_BOOK_DATA_SELECT(){
    log.info("start >>> ");
    Flux<BookDocument> bookAll = bookDocumentRepository.findAll(Sort.by(Order.asc("name")));

    bookAll.subscribe(newBaseSubscriber(1));
    log.info("end >>> ");
    sleep(3000);
  }

  public static void sleep(long ms){
    try{
      Thread.sleep(ms);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

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
