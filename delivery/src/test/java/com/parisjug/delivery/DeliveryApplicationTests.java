package com.parisjug.delivery;

import com.parisjug.delivery.model.LogMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeliveryApplicationTests {

	@Autowired
	private Processor pipe;

	@Autowired
	private MessageCollector messageCollector;

	@Test
	public void whenSendMessage_thenResponseShouldUpdateText() {
		pipe.input()
				.send(MessageBuilder.withPayload(new LogMessage("This is my message"))
						.build());

		Object payload = messageCollector.forChannel(pipe.output())
				.poll()
				.getPayload();

		assertEquals("{\"message\":\"[1]: This is my message\"}", payload.toString());
	}

}

