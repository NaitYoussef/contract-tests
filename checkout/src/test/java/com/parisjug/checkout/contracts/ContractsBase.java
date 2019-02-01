package com.parisjug.checkout.contracts;

import com.parisjug.checkout.domain.Delivery;
import com.parisjug.checkout.domain.Order;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(
        ids = {"com.parisjug:inventory:+:stubs:8080"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        stubsPerConsumer = true)
@AutoConfigureMessageVerifier
@AutoConfigureWebTestClient
public class ContractsBase {

    @Autowired
    private MessageVerifier verifier;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private Delivery delivery;

    @Before
    public void before() {
        RestAssuredWebTestClient.webTestClient(webTestClient);
    }

    public void sendOrder() {
        Order order = new Order("d4d37e73-77a0-4616-8bd2-5ed983d45d14",2, "yannick");
        delivery.send(order);
    }


}
