package com.parisjug.checkout.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CheckoutRouter {

    @Bean
    public RouterFunction<ServerResponse> router(CheckoutHandler checkoutHandler) {
        return route()
                .path("/checkout", builder -> builder
                        .POST("", checkoutHandler::checkoutNow))
                .build();
    }
}
