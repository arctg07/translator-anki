package com.example.application.service;

import com.example.application.aop.annotation.HandleDataForStatistics;
import com.example.application.database.entity.Sentence;
import com.example.application.database.repository.SentenceRepository;
import com.example.application.mapper.SentenceMapper;
import com.example.application.model.EngRusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Iurii Ivanov
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class SentenceService {
    private final SentenceRepository sentenceRepository;

    private final SentenceMapper mapper;

    @HandleDataForStatistics
    public EngRusDto getRandomRusEngSentence(String lang) {
        Sentence sentence = sentenceRepository.getRandomSentence();

        return mapper.toEngRus(sentence);
    }


    @Transactional(readOnly = true)
    public List<EngRusDto> findAllSentences() {
        return sentenceRepository.findAll()
                .stream()
                .map(mapper::toEngRus)
                .collect(Collectors.toList());
    }
}
