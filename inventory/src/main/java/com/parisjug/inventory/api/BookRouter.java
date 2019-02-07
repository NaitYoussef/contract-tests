package com.parisjug.inventory.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> router(BookHandler bookHandler) {
        return route()
                .path("/v1/books", builder -> builder
                        .GET("", accept(APPLICATION_JSON), bookHandler::allBooks)
                        .GET("/{id}", accept(APPLICATION_JSON), bookHandler::book)
                        .POST("", bookHandler::createBook)
                        .POST("/{id}/reduce-stock/{total}", bookHandler::reduceStock))
                .build();
    }

}
