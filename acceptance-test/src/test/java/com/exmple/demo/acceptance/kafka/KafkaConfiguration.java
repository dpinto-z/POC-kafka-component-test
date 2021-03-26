package com.exmple.demo.acceptance.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

@Configuration
public class KafkaConfiguration {

    @Autowired
    private KafkaProperties properties;

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

    @Bean
    public KafkaContainer kafkaContainer() {
        KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));
        kafka.withEmbeddedZookeeper();
        kafka.start();
        String host = kafka.getBootstrapServers();
        properties.setBootstrapServers(Collections.singletonList(host));
        System.out.println("KAFKA running in host " + host);
        return kafka;
    }

    @DependsOn("kafkaContainer")
    @Bean
    public ProducerFactory<?, ?> kafkaProducerFactory(
            ObjectProvider<DefaultKafkaProducerFactoryCustomizer> customizers) {
        DefaultKafkaProducerFactory<?, ?> factory = new DefaultKafkaProducerFactory<>(
                this.properties.buildProducerProperties());
        String transactionIdPrefix = this.properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        customizers.orderedStream().forEach((customizer) -> customizer.customize(factory));
        return factory;
    }

}
