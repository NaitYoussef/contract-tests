package com.parisjug.checkout.provider;


import com.parisjug.checkout.domain.Delivery;
import com.parisjug.checkout.domain.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMessaging implements Delivery {

    private final Streams streams;

    public DeliveryMessaging(Streams streams) {
        this.streams = streams;
    }

    @Override
    public void send(Order order) {
        Message<Order> message = MessageBuilder
                .withPayload(order)
                .setHeader("SENDER", "checkout")
                .build();
        streams.orders().send(message);
    }
}
