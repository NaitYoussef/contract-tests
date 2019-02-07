package com.parisjug.checkout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parisjug.checkout.domain.Book;
import com.parisjug.checkout.domain.Inventory;
import com.parisjug.checkout.domain.Order;
import com.parisjug.checkout.provider.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureStubRunner
@AutoConfigureWebTestClient
public class CheckoutApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private Streams streams;

    @Autowired
    private Inventory inventory;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("unchecked")
    @Test
    void should_checkout_order() throws IOException {
        //when
        Order order = new Order("d4d37e73-77a0-4616-8bd2-5ed983d45d14", 2, "yannick");
        webTestClient
                .post().uri("/v1/checkout")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(order))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class).isEqualTo(order);

        //then
        Message<String> received = (Message<String>) messageCollector.forChannel(streams.orders()).poll();
        Order payload = objectMapper.readValue(Objects.requireNonNull(received).getPayload(), Order.class);
        assertThat(payload).isEqualTo(order);
    }

    @Test
    void should_retrieve_book_from_inventory() {
        Book java = new Book("d4d37e73-77a0-4616-8bd2-5ed983d45d14", "Java", BigDecimal.valueOf(100), 100);
        Book book = inventory.retrieveBook(java.getId()).block(Duration.ofMillis(500));
        assertThat(book).isEqualTo(java);
    }

}

