package com.parisjug.delivery.provider;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface Streams {
	String ORDERS = "orders";

	@Input(Streams.ORDERS)
	MessageChannel orders();
}