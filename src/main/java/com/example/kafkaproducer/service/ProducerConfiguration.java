package com.example.kafkaproducer.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfiguration {
@Bean
    public NewTopic empTopic() {
        return TopicBuilder.name("emp-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
@Bean
    public ProducerFactory<String,Object> producerFactoryObject(){
        Map<String, Object> configsProps = new HashMap<>();
        configsProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        configsProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        configsProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonSerializer");
        //configsProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.kafkaproducer");

        return new DefaultKafkaProducerFactory<>(configsProps);
    }

    @Bean
    public ProducerFactory<String,String> producerFactoryString(){
        Map<String, Object> configsProps = new HashMap<>();
        configsProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        configsProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        configsProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return new DefaultKafkaProducerFactory<>(configsProps);
    }

    @Bean
    public KafkaTemplate<String,Object> kafkaTemplateObject(){
    return new KafkaTemplate<>(producerFactoryObject());
    }
@Bean
    public KafkaTemplate<String,String> kafkaTemplateString() {
        return new KafkaTemplate<String,String>(producerFactoryString());
    }
}
