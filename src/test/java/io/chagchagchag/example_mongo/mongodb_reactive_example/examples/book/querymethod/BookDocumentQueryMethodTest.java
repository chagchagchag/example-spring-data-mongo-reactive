package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.querymethod;

import io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.BookDocument;
import io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.BookDocumentRepository;
import io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.BookNameDocument;
import io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.SaleStatus;
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
public class BookDocumentQueryMethodTest {
  private final Logger log = LoggerFactory.getLogger(BookDocumentQueryMethodTest.class);

  @Autowired
  private BookDocumentRepository bookDocumentRepository;

  @DisplayName("TEST_FIND_QUERY__가격이_가장_높은_책을_조회")
  @Test
  public void TEST_FIND_QUERY__가격이_가장_높은_책을_조회(){
    // given

    // when
    BookDocument book = bookDocumentRepository
        .findFirstByNameOrderByPriceDesc("바람과 함께 사라지다")
        .block();

    // then
    log.info("book.name = " + book.getName());
    log.info("book.price = " + book.getPrice());
    log.info("book.saleStatus = " + book.getSaleStatus());
  }

  @DisplayName("FIND_QUERY__프로젝션_테스트_가장_최근에_발간된_책을_1건_조회")
  @Test
  public void TEST_FIND_QUERY__프로젝션_테스트_가장_최근에_발간된_책을_1건_조회(){
    // given

    // when
    BookNameDocument bookName = bookDocumentRepository
        .findFirstByNameOrderByPublishedAtDesc("바람과 함께 사라지다")
        .block();

    // then
    log.info("book.name = " + bookName.getName());
  }
  
  @DisplayName("DELETE_QUERY__DELETE_테스트_SALE_STATUS_가_HOLD_이면서_가격이_23999원_이상의_책을_삭제")
  @Test
  public void TEST_DELETE_QUERY__DELETE_테스트_SALE_STATUS_가_HOLD_이면서_가격이_23999원_이상의_책을_삭제(){
    // given

    // when
    List<BookDocument> deletedBookList = bookDocumentRepository
        .deleteBySaleStatusAndPriceGreaterThan(SaleStatus.HOLD, BigDecimal.valueOf(23999))
        .toStream()
        .collect(Collectors.toList());

    // then
    log.info(">>> deleted book list ...");
    deletedBookList.stream()
        .forEach(bookDocument -> {
          log.info("book.name = {}, book.price = {}", bookDocument.getName(), bookDocument.getPrice());
        });
  }
  
  @DisplayName("DELETE_QUERY__DELETE_테스트_SALE_STATUS_가_HOLD_인_모든_도큐먼트_삭제")
  @Test
  public void TEST_DELETE_QUERY__DELETE_테스트_SALE_STATUS_가_HOLD_인_모든_도큐먼트_삭제(){
    // given
    
    // when
    BookDocument deleted = bookDocumentRepository
        .deleteBySaleStatus(SaleStatus.HOLD)
        .block();

    // then
    log.info(">>> deleted book");
    log.info(
        "deleted book.name = {}, book.price = {}, book.saleStatus = {}",
        deleted.getName(), deleted.getPrice(), deleted.getSaleStatus()
    );
  }

  @DisplayName("QUERY_ANNOTATION__SALE_STATUS_가_FOR_SALE_인_책들을_조회")
  @Test
  public void TEST_QUERY_ANNOTATION__SALE_STATUS_가_FOR_SALE_인_책들을_조회(){
    // given

    // when
    List<BookDocument> forSaleBooks = bookDocumentRepository
        .findBySaleStatus(SaleStatus.FOR_SALE)
        .toStream()
        .collect(Collectors.toList());

    // then
    forSaleBooks.forEach(bookDocument -> {
      log.info("book.name = {}, book.price = {}, book.saleStatus = {}",
          bookDocument.getName(),
          bookDocument.getPrice(),
          bookDocument.getSaleStatus());
    });
  }

  @DisplayName("QUERY_ANNOTATION__SALE_STATUS_가_FOR_SALE_인_책들의_이름을_글루코스_혁명_이라는_제목으로_업데이트")
  @Test
  public void TEST_QUERY_ANNOTATION__SALE_STATUS_가_FOR_SALE_인_책들의_이름을_글루코스_혁명_이라는_제목으로_업데이트(){
    // given

    // when
    Long updatedBooks = bookDocumentRepository
        .updateBookNameBySaleStatus(SaleStatus.FOR_SALE, "글루코스 혁명")
        .block();

    List<BookDocument> forSaleBooks = bookDocumentRepository
        .findBySaleStatus(SaleStatus.FOR_SALE)
        .toStream()
        .collect(Collectors.toList());

    // then
    log.info("updatedBook count = {}", updatedBooks);

    forSaleBooks.forEach(bookDocument -> {
      log.info("book.name = {}, book.saleStatus = {}", bookDocument.getName(), bookDocument.getSaleStatus());
    });
  }

}
