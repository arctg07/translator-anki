package com.example.application.database.repository;

import com.example.application.database.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Long> {

    @Query(value = "SELECT * FROM anki.dictionary.words WHERE id = (SELECT id FROM anki.dictionary.words ORDER BY RANDOM() LIMIT 1)", nativeQuery = true)
    Sentence getRandomSentence();
}
