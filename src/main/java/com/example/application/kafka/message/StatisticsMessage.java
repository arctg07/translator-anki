package com.example.application.kafka.message;

/**
 * @author Iurii Ivanov
 */

public record StatisticsMessage(String lang, int count, String eng, String rus) {
}
