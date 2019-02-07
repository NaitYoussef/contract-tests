package com.parisjug.checkout.contracts;

import com.parisjug.checkout.domain.Delivery;
import com.parisjug.checkout.domain.Order;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureStubRunner
@AutoConfigureMessageVerifier
@AutoConfigureWebTestClient
public class ContractsBase {

    @Autowired
    private MessageVerifier verifier;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private Delivery delivery;

    @BeforeEach
    public void before() {
        RestAssuredWebTestClient.webTestClient(webTestClient);
    }

    public void sendOrder() {
        Order order = new Order("d4d37e73-77a0-4616-8bd2-5ed983d45d14",2, "yannick");
        delivery.send(order);
    }


}
