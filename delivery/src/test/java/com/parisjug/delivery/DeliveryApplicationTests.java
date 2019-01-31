package com.parisjug.delivery;

import com.parisjug.delivery.domain.DeliveryQueue;
import com.parisjug.delivery.domain.Order;
import com.parisjug.delivery.provider.Streams;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(
        ids = {
                "com.parisjug:inventory:+:stubs:8080",
                "com.parisjug:checkout"
        },
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class DeliveryApplicationTests {

    @Autowired
    private Streams streams;

    @Autowired
    private DeliveryQueue deliveryQueue;

    @Autowired
    StubTrigger stubTrigger;

    @Before
    public void before() {
        deliveryQueue.clear();
    }

    @Test
    public void should_correctly_process_order() {
        Order order = new Order("d4d37e73-77a0-4616-8bd2-5ed983d45d14", 2, "yannick");
        streams.orders().send(MessageBuilder.withPayload(order).build());
        assertThat(deliveryQueue.ordersInProcess()).containsExactly(order);

    }

    @Test
    public void should_correctly_process_order_with_contract_testing() {
        stubTrigger.trigger("should send order");

        Order order = new Order("d4d37e73-77a0-4616-8bd2-5ed983d45d14", 2, "yannick");
        assertThat(deliveryQueue.ordersInProcess()).containsExactly(order);

    }

}

