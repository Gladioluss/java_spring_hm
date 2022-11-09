package com.example.bunshw1;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("dog2Bean")
public class Dog2 implements Pet{

    public Dog2(){
        System.out.println("Dog2 bean is created");
    }
    @PostConstruct
    public void init() {
        System.out.println("class Dog2: Init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("class Dog2: Destroy");
    }

    @Override
    public void say(){
        System.out.println("Gav-Gav");
    }
}
