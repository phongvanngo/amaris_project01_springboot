package com.example.novapo_practice05.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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
    public void beforeController(JoinPoint joinPoint) {
        logger.info("before called " + joinPoint.toString());
    }

//    @Before("execution(* com.example.novapo_practice05.filter.*.*(..))")
//    public void beforeJWTFilter(JoinPoint joinPoint) {
//        logger.trace("before called " + joinPoint.toString());
//    }
//
//    @After("execution(* com.example.novapo_practice05.filter.*.*(..))")
//    public void afterJWTFilter(JoinPoint joinPoint) {
//        logger.trace("after called " + joinPoint.toString());
//    }

    @Before("execution(* com.example.novapo_practice05.service.*.*(..))")
    public void beforeService(JoinPoint joinPoint) {
        logger.info("before called " + joinPoint.toString());
    }

    @After("execution(* com.example.novapo_practice05.service.*.*(..))")
    public void afterService(JoinPoint joinPoint) {
        logger.info("after called " + joinPoint.toString());
    }


}
