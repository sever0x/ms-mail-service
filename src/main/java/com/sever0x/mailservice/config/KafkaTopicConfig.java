package com.sever0x.mailservice.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapAddress;

    @Value("${kafka.topic.email.service}")
    private String emailServiceTopic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = Map.of(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapAddress
        );
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic emailServiceTopic() {
        return new NewTopic(emailServiceTopic, 2, (short) 1);
    }
}
