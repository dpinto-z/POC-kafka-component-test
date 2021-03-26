package com.exmple.demo.acceptance.common.stepdefs;

import com.example.demo.DemoApplication;
import com.exmple.demo.acceptance.kafka.KafkaConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@CucumberContextConfiguration
@SpringBootTest(value = "sever.port=8081", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {DemoApplication.class, KafkaConfiguration.class},
        loader = SpringBootContextLoader.class)
@RunWith(SpringRunner.class)
public abstract class AbstractDefs {

}
