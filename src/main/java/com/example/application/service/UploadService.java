package com.example.application.service;

import com.example.application.database.entity.Sentence;
import com.example.application.database.repository.SentenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Iurii Ivanov
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadService {

    private final SentenceRepository sentenceRepository;

    public void uploadList(InputStream file) {

        log.info("Log.UploadList.start");

        CSVFormat customFormat = CSVFormat.DEFAULT
                .withDelimiter('|')
                .withQuote(null)
                .withEscape('\\')
                .withIgnoreSurroundingSpaces(true);


        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(bufferedReader, customFormat)) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            AtomicInteger atomicInteger = new AtomicInteger();

            csvRecords.forEach(
                    x -> {
                        String row = x.values()[0];
                        System.out.println(atomicInteger.incrementAndGet() + ": " + row);

                        if(checkRow(row)) {
                            //ENGLISH SENTENCE
                            String english = getSentence(row, Character.UnicodeBlock.BASIC_LATIN);
                            if (english.equals("toward")) {
                                log.info("DEBUG");
                            }
                            System.out.println("include eng: " + english);

                            //RUSSIAN SENTENCE
                            String russian = getSentence(row, Character.UnicodeBlock.CYRILLIC);
                            System.out.println("include rus: : " + russian);

                            Sentence sentence = Sentence.builder()
                                    .eng(english)
                                    .rus(russian)
                                    .build();

                            save(sentence);
                        }

                    }
            );
        } catch (IOException ex) {
            log.error("Log.UploadList.error: " + ex.getMessage(), ex);
        }

        log.info("Log.UploadList.end");
    }

    private boolean checkRow(String input) {
       return checkLength(input) && checkBothLanguage(input);
    }

    private boolean checkLength(String input) {
        return input.length() >= 4;
    }

    private boolean checkBothLanguage(String input) {

        boolean rus = false;
        boolean eng = false;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetter(ch)) {
                Character.UnicodeBlock unicode = Character.UnicodeBlock.of(ch);
                if (unicode == Character.UnicodeBlock.BASIC_LATIN) {
                    rus = true;
                } else if (unicode == Character.UnicodeBlock.CYRILLIC) {
                    eng = true;
                }
            }
        }
        return rus && eng;
    }

    private String getSentence(String input, Character.UnicodeBlock lang) {
        String russianSentence = "";

        int firstIndex = -1;
        int lastIndex = -1;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetter(ch)) {
                if (Character.UnicodeBlock.of(ch) == lang) {
                    if (firstIndex == -1) {
                        firstIndex = i;
                    } else {
                        lastIndex = i;
                    }
                }
            }
            char c = input.charAt(i);
            if(i == lastIndex + 1 && c == '?' && lastIndex > 1) {
                lastIndex = i;
            }
        }

        if (lastIndex == -1) {
            russianSentence = input.substring(firstIndex);
        } else {
            russianSentence = input.substring(firstIndex, lastIndex + 1);
        }

        return russianSentence;
    }

    private Sentence save(Sentence sentence) {
        return sentenceRepository.save(sentence);
    }
}
