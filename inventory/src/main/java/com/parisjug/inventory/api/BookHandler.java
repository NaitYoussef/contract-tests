package com.parisjug.inventory.api;

import com.parisjug.inventory.domain.Book;
import com.parisjug.inventory.domain.BookIdGenerator;
import com.parisjug.inventory.infra.BookInventory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class BookHandler {

    private final BookInventory bookInventory;
    private final BookIdGenerator bookIdGenerator;

    public BookHandler(BookInventory bookInventory, BookIdGenerator bookIdGenerator) {
        this.bookInventory = bookInventory;
        this.bookIdGenerator = bookIdGenerator;
    }

    public Mono<ServerResponse> allBooks(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(bookInventory.allBooks(), Book.class));
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        Mono<Book> newBook = request.bodyToMono(Book.class)
                .map(this::assignRandomId)
                .doOnNext(bookInventory::addBook);
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(newBook, Book.class);
    }

    public Mono<ServerResponse> reduceStock(ServerRequest request) {
        String bookId = request.pathVariable("id");
        Integer total = Integer.parseInt(request.pathVariable("total"));
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(bookInventory.reduceStock(bookId, total), Integer.class));
    }

    private Book assignRandomId(Book book) {
        book.setId(bookIdGenerator.randomId());
        return book;
    }
}