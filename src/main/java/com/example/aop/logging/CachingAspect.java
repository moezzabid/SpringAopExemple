package com.example.aop.logging;


import com.example.aop.entity.Passenger;
import com.example.aop.service.impl.PassangerImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class CachingAspect {
    private final PassangerImpl passangerImpl ;

    public CachingAspect(PassangerImpl passangerImpl) {
        this.passangerImpl = passangerImpl;
    }

    @Around("execution(* com.example.aop.service.impl.PassangerImpl.getPassengerById(..))")
    public void cachePassenger(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Object[]methodArgs =thisJoinPoint.getArgs();
        Passenger result=(Passenger)thisJoinPoint.proceed();
        Long id = (Long) methodArgs[0];

        if(!passangerImpl.getPassangersMap().containsKey(id)){
            passangerImpl.getPassangersMap().put(id,result);
        }
        System.out.println("moezzzzzz");
    }

}
