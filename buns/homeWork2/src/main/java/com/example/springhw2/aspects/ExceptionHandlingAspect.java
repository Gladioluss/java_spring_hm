package com.example.springhw2.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(30)
public class ExceptionHandlingAspect {
    @Before("com.example.springhw2.aspects.Pointcuts.allAddMethods()")
    public void beforeGetExceptionHandlingAdvice(){
        System.out.println(
                "beforeGetExceptionHandlingAdvice: ловим/обрабатываем исключение при попытке получить книгу/журнал");
    }
}
