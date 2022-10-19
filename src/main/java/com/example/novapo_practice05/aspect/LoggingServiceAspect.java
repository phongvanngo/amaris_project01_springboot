package com.example.novapo_practice05.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingServiceAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingServiceAspect.class);

    @Before("execution(* com.example.novapo_practice05.web.rest.*.*(..))")
    public void before(JoinPoint joinPoint) {
        logger.info("before called " + joinPoint.toString());
    }
}
