package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.aggregation;

import io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.BookDocumentAggregateRepository;
import io.chagchagchag.example_mongo.mongodb_reactive_example.examples.book.BookDocumentRepository;
import io.chagchagchag.example_mongo.mongodb_reactive_example.util.DelayUtil;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// TODO : 문서작업 모두 끝나고 나면 TestContainers 버전으로 전환하기
@SpringBootTest
public class BookDocumentAggregationTest {
  private Logger log = LoggerFactory.getLogger(BookDocumentAggregationTest.class);

  @Autowired
  private BookDocumentRepository bookDocumentRepository;

  @Autowired
  private BookDocumentAggregateRepository bookDocumentAggregateRepository;
  
  @DisplayName("REMOTE_AGGREGATION_PIPELINE_책_제목으로_검색한_결과에_대해_SALE_STATUS_별_카운팅_집계를_수행")
  @Test
  public void TEST_REMOTE_AGGREGATION_PIPELINE_책_제목으로_검색한_결과에_대해_SALE_STATUS_별_카운팅_집계를_수행(){
    // given
    
    // when
    var b = bookDocumentAggregateRepository
        .aggregateGroupByStatusAndCounting(BigDecimal.valueOf(10000), BigDecimal.valueOf(25000))
        .toStream()
        .collect(Collectors.toList());

    b.stream().forEach(bookCountAggregate -> {
      log.info("status = " + bookCountAggregate.status());
      log.info("cnt = " + bookCountAggregate.cnt());
    });

    DelayUtil.delayMs(10*1000);
  }
}
