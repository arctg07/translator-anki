package com.example.application.aop.helper;

import com.example.application.aop.event.WordHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Iurii Ivanov
 *
 * CHAIN OF RESPONSIBILITY
 */

@Component
public class HandlerFactory {

    @Autowired
    List<WordHandler> handlerList;

    public WordHandler getHandler(String language) {
        return handlerList.stream()
                .filter(x -> x.getWordsLanguage(language))
                .findFirst()
                .orElse(null);
    }
}
