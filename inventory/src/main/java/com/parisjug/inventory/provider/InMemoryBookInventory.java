package com.parisjug.inventory.provider;

import com.parisjug.inventory.domain.Book;
import com.parisjug.inventory.domain.BookInventory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryBookInventory implements BookInventory {

    private final Map<String, Book> inventory = new HashMap<>();

    @PostConstruct
    void initDB() {
        Book java = new Book("d4d37e73-77a0-4616-8bd2-5ed983d45d14", "Java", BigDecimal.valueOf(100), 100);
        addBook(java);
    }

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
