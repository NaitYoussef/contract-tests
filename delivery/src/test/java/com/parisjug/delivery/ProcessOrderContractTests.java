package com.parisjug.delivery;

import com.parisjug.delivery.domain.DeliveryQueue;
import com.parisjug.delivery.domain.Order;
import com.parisjug.delivery.provider.Streams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureStubRunner(
        ids = {
                "com.parisjug:inventory:+:stubs:8080",
                "com.parisjug:checkout"
        },
        stubsMode = StubRunnerProperties.StubsMode.REMOTE,
        repositoryRoot = "git://https://github.com/ygrenzinger/contract-tests.git")
public class ProcessOrderContractTests {

    @Autowired
    private Streams streams;

    @Autowired
    private DeliveryQueue deliveryQueue;

    @Autowired
    private StubTrigger stubTrigger;

    @BeforeEach
    void before() {
        deliveryQueue.clear();
    }

    @Test
    void should_correctly_process_order() {
        stubTrigger.trigger("should send order");
        Order order = new Order("d4d37e73-77a0-4616-8bd2-5ed983d45d14", 2, "yannick");
        assertThat(deliveryQueue.ordersInProcess()).containsExactly(order);
    }

}

