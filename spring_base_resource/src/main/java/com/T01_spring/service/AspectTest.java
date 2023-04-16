package com.T01_spring.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTest {

    @Before("execution(public void com.T01_spring.test.service.UserService.test())")
    public void before(JoinPoint point){
        System.out.println("aspect before");
    }
}
