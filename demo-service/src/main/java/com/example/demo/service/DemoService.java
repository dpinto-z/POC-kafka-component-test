package com.example.demo.service;

import com.example.demo.Demo;
import com.example.demo.clients.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DemoService {
    private KafkaProducer kafkaProducer;
    public void publish(String value) {
        kafkaProducer.produce(Demo.builder().something(value).build());
    }
}
