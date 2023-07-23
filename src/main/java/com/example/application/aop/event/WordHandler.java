package com.example.application.aop.event;

/**
 * @author Iurii Ivanov
 */

public interface WordHandler {

    void handleWord(String word);

    boolean getWordsLanguage(String language);
}
