package com.example.demo.clients;

import com.example.demo.Demo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplateString;
    private final NewTopic topic;

    public void produce(Demo demo) {
        kafkaTemplateString.send(topic.name(), demo.getSomething(), demo.getSomething()).addCallback(
                result -> {
                    final RecordMetadata m;
                    if (result != null) {
                        m = result.getRecordMetadata();
                        log.info("Produced record to topic {} partition {} @ offset {}",
                                m.topic(),
                                m.partition(),
                                m.offset());
                    }
                },
                exception -> log.error("Failed to produce to kafka", exception));
        kafkaTemplateString.flush();

    }
}
