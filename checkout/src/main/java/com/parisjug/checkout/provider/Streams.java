package com.parisjug.checkout.provider;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Streams {
    String ORDERS = "orders";

    @Output(Streams.ORDERS)
    MessageChannel orders();
}
