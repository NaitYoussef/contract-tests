package com.parisjug.delivery;

import com.parisjug.delivery.domain.Book;
import com.parisjug.delivery.domain.DeliveryQueue;
import com.parisjug.delivery.domain.Inventory;
import com.parisjug.delivery.domain.Order;
import com.parisjug.delivery.provider.Streams;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessOrderTests {

    @Autowired
    private Streams streams;

    @Autowired
    private DeliveryQueue deliveryQueue;

    @MockBean
    private Inventory inventory;

    @Before
    public void before() {
        deliveryQueue.clear();
    }

    @Test
    public void should_correctly_process_order() {
        String bookId = "d4d37e73-77a0-4616-8bd2-5ed983d45d14";
        Book book = new Book(bookId, "Java", BigDecimal.valueOf(100), 100);
        Mockito.when(inventory.retrieveBook(bookId)).thenReturn(Mono.justOrEmpty(book));
        Order order = new Order(bookId, 2, "yannick");
        streams.orders().send(MessageBuilder.withPayload(order).build());
        assertThat(deliveryQueue.ordersInProcess()).containsExactly(order);

    }

}

