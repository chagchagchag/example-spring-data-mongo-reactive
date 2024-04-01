package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.transactional;

import io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.BookDocument;
import io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.BookDocumentService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDocumentTransactionalTest {
  private static Logger log = LoggerFactory.getLogger(BookDocumentTransactionalTest.class);

  @Autowired
  private BookDocumentService sut;

  @DisplayName("TEST_새로운_책을_트랜잭셔널_애노테이션을_이용해_저장_및_수정")
  @Test
  public void TEST_새로운_책을_트랜잭셔널_애노테이션을_이용해_저장_및_수정(){
    // given

    // when

    // then
    log.info("before save");
    List<BookDocument> result = sut.insertNewBook("맛도리 여행", BigDecimal.valueOf(3000))
        .toStream()
        .collect(Collectors.toList());
    log.info("after save, result = {}", result);
  }
}