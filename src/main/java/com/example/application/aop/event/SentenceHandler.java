package com.example.application.aop.event;

import com.example.application.kafka.KafkaProducer;
import com.example.application.kafka.message.StatisticsMessage;
import com.example.application.model.EngRusDto;
import lombok.RequiredArgsConstructor;

/**
 * @author Iurii Ivanov
 */

@RequiredArgsConstructor
public abstract class SentenceHandler implements WordHandler {

    private final KafkaProducer producer;

    void sendStatistics(String lang, int count, EngRusDto sentence) {
        StatisticsMessage statisticsMessage = new StatisticsMessage(
                lang,
                count,
                sentence
        );
        producer.sendStatistics(statisticsMessage);
    }

}
