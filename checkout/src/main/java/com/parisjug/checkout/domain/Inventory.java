package com.parisjug.checkout.domain;

import reactor.core.publisher.Mono;

public interface Inventory {
    Mono<Book> retrieveBook(String bookId);
}
