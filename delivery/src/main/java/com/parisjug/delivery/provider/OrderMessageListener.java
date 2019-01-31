package com.parisjug.delivery.provider;


import com.parisjug.delivery.domain.Order;
import com.parisjug.delivery.domain.OrderProcessor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageListener {

    private final OrderProcessor orderProcessor;

    public OrderMessageListener(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    @StreamListener(Streams.ORDERS)
    public void processOrder(Order order) {
        orderProcessor.processOrder(order);
    }
}
