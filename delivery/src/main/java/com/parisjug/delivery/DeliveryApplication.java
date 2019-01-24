package com.parisjug.delivery;

import com.parisjug.delivery.messages.TextPlainMessageConverter;
import com.parisjug.delivery.model.LogMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.SendTo;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication
@EnableBinding(Processor.class)
public class DeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}

	@StreamEmitter
	@Output(Processor.INPUT)
	public Flux<LogMessage> emit() {
		return Flux
				.interval(Duration.ofMillis(1000))
				.map(l -> LogMessage.randomMessage());
	}

	@StreamListener
	@Output(Processor.OUTPUT)
	public Flux<LogMessage> enrichLogMessage(@Input(Processor.INPUT) Flux<LogMessage> input) {
		return input
				.doOnEach(log -> System.out.println(log.get()))
				.map(log -> new LogMessage(String.format("[1]: %s", log.getMessage())));
	}
	
}

