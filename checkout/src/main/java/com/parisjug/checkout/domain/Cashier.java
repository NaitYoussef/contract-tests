package com.parisjug.checkout.domain;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class Cashier {
    private final Inventory inventory;
    private final Delivery delivery;

    public Cashier(Inventory inventory, Delivery delivery) {
        this.inventory = inventory;
        this.delivery = delivery;
    }

    public Mono<Order> checkoutNow(Mono<Order> order) {
        return order
                .flatMap(this::checkStock)
                .doOnSuccess(delivery::send);
    }

    private Mono<Order> checkStock(Order order) {
        return this.inventory
                .retrieveBook(order.getBookId())
                .flatMap(book -> {
                    if (book.getStock() < order.getNumber()) {
                        return Mono.error(new NoMoreStockException());
                    }
                    return Mono.fromSupplier(() -> order);
                });
    }
}
