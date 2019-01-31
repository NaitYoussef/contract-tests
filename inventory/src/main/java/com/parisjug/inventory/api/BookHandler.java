package com.parisjug.inventory.api;

import com.parisjug.inventory.domain.Book;
import com.parisjug.inventory.domain.BookIdGenerator;
import com.parisjug.inventory.infra.InMemoryBookInventory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class BookHandler {

    private final InMemoryBookInventory bookInventory;
    private final BookIdGenerator bookIdGenerator;

    public BookHandler(InMemoryBookInventory bookInventory, BookIdGenerator bookIdGenerator) {
        this.bookInventory = bookInventory;
        this.bookIdGenerator = bookIdGenerator;
    }
    Mono<ServerResponse> allBooks(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(bookInventory.allBooks(), Book.class);
    }

    Mono<ServerResponse> createBook(ServerRequest request) {
        Mono<Book> newBook = request.bodyToMono(Book.class)
                .map(this::assignRandomId)
                .doOnNext(bookInventory::addBook);
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(newBook, Book.class);
    }

    Mono<ServerResponse> reduceStock(ServerRequest request) {
        String bookId = request.pathVariable("id");
        Integer total = Integer.parseInt(request.pathVariable("total"));
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(bookInventory.reduceStock(bookId, total), Integer.class));
    }

    Mono<ServerResponse> book(ServerRequest request) {
        String bookId = request.pathVariable("id");
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(bookInventory.findBook(bookId), Book.class);
    }

    private Book assignRandomId(Book book) {
        book.setId(bookIdGenerator.randomId());
        return book;
    }
}
