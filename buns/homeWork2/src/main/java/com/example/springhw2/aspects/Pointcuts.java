package com.example.springhw2.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(public void add*(*))")
    public void allAddMethods(){}
}
