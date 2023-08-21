package com.example.application.kafka;

import com.example.application.kafka.message.StatisticsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Iurii Ivanov
 */

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${topic.send-analytics}")
    private String topic;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendStatistics(StatisticsMessage message) {
        kafkaTemplate.send(topic, message.lang(), message);
    }
}
