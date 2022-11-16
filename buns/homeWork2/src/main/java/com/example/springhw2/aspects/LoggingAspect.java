package com.example.springhw2.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(10)
public class LoggingAspect {

//    @Before("com.example.springhw2.aspects.Pointcuts.allAddMethods()")
//    public void beforeAddBookAdvice(JoinPoint joinPoint){
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        System.out.println("methodSignature: " + methodSignature);
//        System.out.println("methodSignature.getMethod(): " + methodSignature.getMethod());
//        System.out.println("beforeGetBookAdvice: попытка получить книгу/журнал");
//    }



    @Pointcut("execution(* com.example.springhw2.UniLibrary.*(..))")
    private void allMethodsFromUniLibrary(){}

    @Pointcut("execution(public void com.example.springhw2.UniLibrary.returnMagazine())")
    private void returneMagazineFromUniLibrary(){}

    @Pointcut("allMethodsFromUniLibrary() && !returneMagazineFromUniLibrary()")
    private void allMethodsExeptReturnsMagazineUniLibrary(){}

    @Before("allMethodsExeptReturnsMagazineUniLibrary()")
    public void beforeGetLoggingAdvice(JoinPoint joinPoint){
        System.out.println("allMethodsExeptReturnsMagazineUniLibrary: writing Log#1");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("methodSignature: " + methodSignature);
        System.out.println("methodSignature.getMethod(): " + methodSignature.getMethod());
        System.out.println("methodSignature.getParameterNames(): " + methodSignature.getParameterNames());
        System.out.println("beforeGetBookAdvice: попытка получить книгу/журнал");
    }

//    @Pointcut("execution(* com.example.springhw2.UniLibrary.get*())")
//    private void allGetMethodsFromUniLibrary(){}
//
//    @Pointcut("execution(* com.example.springhw2.UniLibrary.return*())")
//    private void allReturnMethodsFromUniLibrary(){}
//
//    @Pointcut("allGetMethodsFromUniLibrary() || allReturnMethodsFromUniLibrary()")
//    private void allGetAndReturnMethodsFromUniLibrary(){}
//
//    @Before("allGetMethodsFromUniLibrary()")
//    public void beforeGetLoggingAdvice(){
//        System.out.println("beforeGetLoggingAdvice: writing Log#1");
//    }
//
//    @Before("allReturnMethodsFromUniLibrary()")
//    public void beforeReturnLoggingAdvice(){
//        System.out.println("beforeReturnLoggingAdvice: writing Log#2");
//    }
//
//    @Before("allGetAndReturnMethodsFromUniLibrary()")
//    public void beforeGetAndReturnLoggingAdvice(){
//        System.out.println("beforeGetAndReturnLoggingAdvice: writing Log#3");
//    }

//    @Pointcut("execution(public void get*())")
//    private void allGetMethods(){}
//
//    @Before("allGetMethods()")
//    public void beforeGetBookAdvice(){
//        System.out.println("beforeGetBookAdvice: попытка получить книгу/журнал");
//    }
//
//    @Before("allGetMethods()")
//    public void beforeGetSecurityAdvice(){
//        System.out.println("beforeGetSecurityAdvice: проверка прав на получение книги/журнала");
//    }

//    @Before("execution(public void getBook(com.example.springhw2.Book))")
//    public void beforeGetBookAdvice(){
//        System.out.println("beforeGetBookAdvice: попытка получить книгу");
//    }

//    @Before("execution( * returnBook())")
//    public void beforeReturnBookAdvice(){
//        System.out.println("beforeReturnBookAdvice: попытка вернуть книгу");
//    }
}
