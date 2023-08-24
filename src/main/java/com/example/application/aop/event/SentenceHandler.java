package com.example.application.aop.event;

import com.example.application.kafka.KafkaProducer;
import com.example.application.kafka.message.StatisticsMessage;
import com.example.application.model.EngRusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Iurii Ivanov
 */

@RequiredArgsConstructor
@Slf4j
public abstract class SentenceHandler implements WordHandler {

    private final KafkaProducer producer;

    void sendStatistics(String lang, int count, EngRusDto sentence) {
        StatisticsMessage statisticsMessage = new StatisticsMessage(
                lang,
                count,
                sentence
        );

        try {
            producer.sendStatistics(statisticsMessage);
        } catch (Exception ex) {
            log.error("KAFKA ERROR" + "\n" +  ex.getLocalizedMessage());
        }
    }

}
