package com.example.aop.logging;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

import java.util.logging.Logger;

@Aspect
@Order(1)
public class LoggingAspect {

    private Logger logger =Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(com.example.aop.service.impl.*) || within(com.example.aop.web.*)")
    public void loggingPointcut() {
    }
    //methode doit affiche un message avant d'executer le methode getPassengerById
    @Before("execution(public String com.example.aop.entity.Passenger.getId())")
    public void Before(){
        logger.info("Entering method");
    }

    //methode doit affiche un message aprés l'execution des methodes dans la classe service
    @AfterReturning(pointcut = "loggingPointcut() ")
    public void after(){
        logger.info("I will be called a id  method");
    }

    // cette methode sera execute autour de l'execution de toute les methodes que se termine par Passenger
    @Around("loggingPointcut()")
    public Object log(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodeName =thisJoinPoint.getSignature().getName();
        Object[] methodArgs =thisJoinPoint.getArgs();
        logger.info("Call method " +methodeName+"with arg"+methodArgs[0]);
        Object result=thisJoinPoint.proceed();
        logger.info("method"+methodeName+ "returns" + result);
        return result ;
    }
}
