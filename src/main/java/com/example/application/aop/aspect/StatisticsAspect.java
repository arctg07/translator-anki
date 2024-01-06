package com.example.application.aop.aspect;

import com.example.application.aop.event.WordHandler;
import com.example.application.aop.helper.HandlerFactory;
import com.example.application.model.EngRusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Iurii Ivanov
 */

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class StatisticsAspect {

    private final HandlerFactory handlerFactory;
    @Around("@annotation(com.example.application.aop.annotation.HandleDataForStatistics)")
    public EngRusDto handleEvents(ProceedingJoinPoint joinPoint) throws Throwable {


        Object[] args = joinPoint.getArgs();
        String lang = (String) args[0];
        WordHandler handler = handlerFactory.getHandler(lang);

        EngRusDto result = (EngRusDto) joinPoint.proceed();

        handler.handleWord(result);

        return result;
    }
}
