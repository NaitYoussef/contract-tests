package com.parisjug.delivery.domain;

import reactor.core.publisher.Mono;

public interface Inventory {
    Mono<Book> retrieveBook(String bookId);
}
