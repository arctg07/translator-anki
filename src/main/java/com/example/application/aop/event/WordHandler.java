package com.example.application.aop.event;

import com.example.application.model.EngRusDto;

/**
 * @author Iurii Ivanov
 */

public interface WordHandler {

    void handleWord(EngRusDto word);

    boolean getWordsLanguage(String language);

}
