package com.exmple.demo.acceptance.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfiguration {
    @Autowired
    private KafkaMessageCache<String> stringMessage;

    @Bean
    public KafkaMessageCache<String> stringMessage() {
        return new KafkaMessageCache<>();
    }

    @KafkaListener(topics = "#{'${io.confluent.developer.config.topic.name}'}")
    public void consume(final ConsumerRecord<String, String> consumerRecord) {
        stringMessage.addMessage(consumerRecord.value());
    }

}
