package com.example.application.aop.event;

import com.example.application.kafka.KafkaProducer;
import com.example.application.model.EngRusDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Iurii Ivanov
 */

@Component
@Slf4j
public class RusWordHandler extends SentenceHandler {
    AtomicInteger count = new AtomicInteger();

    {
        count.set(0);
    }

    public RusWordHandler(KafkaProducer producer) {
        super(producer);
    }

    @Override
    public void handleWord(EngRusDto word) {
        log.info("TRANSLATE FROM RUSSIAN TIMES: {}", count.incrementAndGet());
        sendStatistics("russian", count.get() ,word);
    }

    @Override
    public boolean getWordsLanguage(String word) {
        return word.equals("russian");
    }
}
