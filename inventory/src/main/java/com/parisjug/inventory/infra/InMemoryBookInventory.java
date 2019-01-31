package com.parisjug.inventory.infra;

import com.parisjug.inventory.domain.Book;
import com.parisjug.inventory.domain.BookInventory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryBookInventory implements BookInventory {

    private final Map<String, Book> inventory = new HashMap<>();

    @Override
    public Flux<Book> allBooks() {
        return Flux.fromIterable(inventory.values());
    }

    @Override
    public Mono<Book> findBook(String bookId) {
        return Mono.fromSupplier(() -> inventory.get(bookId));
    }

    @Override
    public void addBook(Book book) {
        inventory.put(book.getId(), book);
    }

    @Override
    public void removeAllStocks() {
        inventory.clear();
    }

    @Override
    public Mono<Integer> reduceStock(String bookId, Integer total) {
        Book book = inventory.get(bookId);
        int remaining = book.getStock() - total;
        book.setStock(remaining);
        return Mono.fromSupplier(() -> remaining);
    }
}
