package com.parisjug.checkout.provider;


import com.parisjug.checkout.domain.Delivery;
import com.parisjug.checkout.domain.Order;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageClientInventory implements Delivery {

    private final Processor processor;

    public MessageClientInventory(Processor processor) {
        this.processor = processor;
    }

    @Override
    public void send(Order order) {
        processor.output().send(MessageBuilder.withPayload(order).build());
    }
}
