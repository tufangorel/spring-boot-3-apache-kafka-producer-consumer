package com.kafka.producer.api.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(3)
public class RequestAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestAspect.class);

    @Around(value="@annotation(org.springframework.web.bind.annotation.GetMapping)"+
                  " || @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object beforeAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object result = "";
        try{
            LOGGER.info("Before proceed");
            String method = proceedingJoinPoint.getSignature().getName();
            String parameters = Arrays.toString(proceedingJoinPoint.getArgs());
            LOGGER.info("Method [ "+method+" ] called with parameters : [ "+parameters+" ]");
            result = proceedingJoinPoint.proceed();
            LOGGER.info("After proceed");
        }
        catch( Exception exception ) {
            LOGGER.error(exception.getMessage());
            throw exception;
        }
        return result;
    }
}
