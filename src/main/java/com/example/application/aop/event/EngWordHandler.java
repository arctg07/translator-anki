package com.example.application.aop.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Iurii Ivanov
 */

@Component
@Slf4j
public class EngWordHandler implements WordHandler {

    AtomicInteger count = new AtomicInteger();

    {
        count.set(0);
    }

    @Override
    public void handleWord(String word) {
        log.info("TRANSLATE FROM ENGLISH TIMES: {}", count.incrementAndGet());
    }

    @Override
    public boolean getWordsLanguage(String word) {
        return word.equals("english");
    }
}
