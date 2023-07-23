package com.example.application.aop.aspect;

import com.example.application.aop.event.WordHandler;
import com.example.application.aop.helper.HandlerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Iurii Ivanov
 */

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class StatisticsAspect {

    private final HandlerFactory handlerFactory;
    @Before("@annotation(com.example.application.aop.annotation.HandleDataForStatistics)")
    public void handleEvents(JoinPoint jp) throws Throwable {

        Object[] args = jp.getArgs();
        String lang = (String) args[0];

        WordHandler handler = handlerFactory.getHandler(lang);

        handler.handleWord(lang);
    }
}
