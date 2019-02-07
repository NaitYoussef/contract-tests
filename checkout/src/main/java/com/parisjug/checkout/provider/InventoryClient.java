package com.parisjug.checkout.provider;

import com.parisjug.checkout.domain.Book;
import com.parisjug.checkout.domain.Inventory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class InventoryClient implements Inventory {
    private WebClient client = WebClient.create("http://localhost:8080");

    @Override
    public Mono<Book> retrieveBook(String bookId) {
        return client.get()
                .uri("/v1/books/{id}", bookId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json")
                .retrieve()
                .bodyToMono(Book.class);
    }
}
