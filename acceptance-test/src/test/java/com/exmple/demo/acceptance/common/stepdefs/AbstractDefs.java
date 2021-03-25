package com.exmple.demo.acceptance.common.stepdefs;

import com.example.demo.DemoApplication;
import com.exmple.demo.acceptance.kafka.KafkaConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(value = "sever.port=8081", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {DemoApplication.class, KafkaConfiguration.class},
        loader = SpringBootContextLoader.class)
public abstract class AbstractDefs {
}
