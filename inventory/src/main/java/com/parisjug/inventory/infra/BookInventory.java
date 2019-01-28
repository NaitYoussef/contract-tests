package com.parisjug.inventory.infra;

import com.parisjug.inventory.domain.Book;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookInventory {

    private final Map<String, Book> inventory = new HashMap<>();

    public Flux<Book> allBooks() {
        return Flux.fromIterable(inventory.values());
    }

    public Mono<Book> findBook(String bookId) {
        return Mono.fromSupplier(() -> inventory.get(bookId));
    }

    public void addBook(Book book) {
        inventory.put(book.getId(), book);
    }

    public void removeAllStocks() {
        inventory.clear();
    }

    public Mono<Integer> reduceStock(String bookId, Integer total) {
        Book book = inventory.get(bookId);
        int remaining = book.getStock() - total;
        book.setStock(remaining);
        return Mono.fromSupplier(() -> remaining);
    }
}
