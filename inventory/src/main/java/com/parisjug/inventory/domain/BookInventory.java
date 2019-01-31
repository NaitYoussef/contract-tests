package com.parisjug.inventory.domain;

import com.parisjug.inventory.domain.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookInventory {
    Flux<Book> allBooks();
    Mono<Book> findBook(String bookId);
    void addBook(Book book);
    void removeAllStocks();
    Mono<Integer> reduceStock(String bookId, Integer total);
}
