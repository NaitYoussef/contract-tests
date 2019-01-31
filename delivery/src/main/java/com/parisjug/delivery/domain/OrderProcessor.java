package com.parisjug.delivery.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
public class OrderProcessor {
    private final Inventory inventory;
    private final DeliveryQueue deliveryQueue;

    public OrderProcessor(Inventory inventory, DeliveryQueue deliveryQueue) {
        this.inventory = inventory;
        this.deliveryQueue = deliveryQueue;
    }

    public void processOrder(Order order) {
        Book book = inventory.retrieveBook(order.getBookId()).block(Duration.ofMillis(5000));
        if (book == null || (order.getNumber() > book.getStock())) {
            log.info("Adding order in error {}", order);
            deliveryQueue.addInErrorOrder(order);
        } else {
            log.info("Adding order in process {}", order);
            deliveryQueue.addInProcessOrder(order);
        }

    }
}
