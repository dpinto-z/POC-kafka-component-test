package com.exmple.demo.acceptance.component.stepdefs;

import com.exmple.demo.acceptance.common.stepdefs.AbstractDefs;
import com.exmple.demo.acceptance.kafka.KafkaMessageCache;
import com.jayway.restassured.response.Response;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class BasicScenario {

    Response response;
    @Autowired
    private KafkaMessageCache<String> stringMessage;
    @Value("${io.confluent.developer.config.topic.name}")
    private String topic;
    @Autowired
    NewTopic moviesTopic;

    @Given("^The service is available$")
    public void helloScenario() {
    }

    @When("^we hit hello endpoint to sent a kafka message$")
    public void call() {
        response = given().get("/hello");
        response.prettyPrint();
    }

    @Then("^we should publish a kafka message$")
    public void response() {
        assertEquals("ping", stringMessage.getFirstMessage());
    }
}
