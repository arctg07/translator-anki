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
public class EngWordHandler extends SentenceHandler {

    AtomicInteger count = new AtomicInteger();

    {
        count.set(0);
    }

    public EngWordHandler(KafkaProducer producer) {
        super(producer);
    }

    @Override
    public void handleWord(EngRusDto word) {
        log.info("TRANSLATE FROM ENGLISH TIMES: {}", count.incrementAndGet());
        sendStatistics("english", count.get(), word);
    }

    @Override
    public boolean getWordsLanguage(String word) {
        return word.equals("english");
    }
}
