package com.parisjug.inventory.contracts;

import com.parisjug.inventory.domain.Book;
import com.parisjug.inventory.domain.BookIdGenerator;
import com.parisjug.inventory.infra.InMemoryBookInventory;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class ContractsBase {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private InMemoryBookInventory bookInventory;

    @MockBean
    private BookIdGenerator bookIdGenerator;

    @Before
    public void before() {
        Book java = new Book("d4d37e73-77a0-4616-8bd2-5ed983d45d14", "Java", BigDecimal.valueOf(100), 100);
        Book kotlin = new Book("8364948b-6221-4cd8-9fd9-db0d17d45ef8", "Kotlin", BigDecimal.valueOf(120), 20);
        bookInventory.addBook(java);
        bookInventory.addBook(kotlin);

        Mockito.when(bookIdGenerator.randomId()).thenReturn("dc8493d6-e2e3-47da-a806-d1e8ff7cd4df");

        RestAssuredWebTestClient.webTestClient(webTestClient);
    }

    @After
    public void after() {
        bookInventory.removeAllStocks();
    }

}
