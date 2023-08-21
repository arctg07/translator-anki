package com.example.application.kafka.message;

import com.example.application.model.EngRusDto;

/**
 * @author Iurii Ivanov
 */

public record StatisticsMessage(String lang, int count, EngRusDto sentence) {
}
