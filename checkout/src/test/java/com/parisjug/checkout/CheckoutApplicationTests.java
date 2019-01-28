package com.parisjug.checkout;

import com.parisjug.checkout.domain.Book;
import com.parisjug.checkout.domain.Inventory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(
		ids = {"com.parisjug:inventory:+:stubs:8080"},
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@AutoConfigureWebTestClient
public class CheckoutApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private Inventory inventory;

	@Test
	public void should_retrieve_book_from_inventory() {

		Book java = new Book("d4d37e73-77a0-4616-8bd2-5ed983d45d14","Java", BigDecimal.valueOf(100), 100);
		Book book = inventory.retrieveBook(java.getId()).block(Duration.ofMillis(500));
		assertThat(book).isEqualTo(java);
	}

}

