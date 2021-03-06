package com.parisjug.checkout.api;

import com.parisjug.checkout.domain.Cashier;
import com.parisjug.checkout.domain.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class CheckoutHandler {

    private final Cashier cashier;

    public CheckoutHandler(Cashier cashier) {
        this.cashier = cashier;
    }

    Mono<ServerResponse> checkoutNow(ServerRequest request) {
        Mono<Order> body = request.bodyToMono(Order.class);
        Mono<Order> order = cashier.checkoutNow(body);
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(order, Order.class));
    }
}
