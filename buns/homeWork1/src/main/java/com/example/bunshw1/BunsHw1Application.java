package com.example.bunshw1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class BunsHw1Application {

    public static void main(String[] args) {

//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
//        SpringApplication.run(BunsHw1Application.class, args);

    }
    public static void test1(){
        Pet pet = new Dog();
        pet.say();

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Pet pet2 = context.getBean("myPet", Pet.class);
        pet2.say();

        Pet pet3 = context.getBean("myPet", Pet.class);
        Person person = new Person(pet3);
        person.callYourPet();

        Person person1 = context.getBean("myPerson2", Person.class);
        person1.callYourPet();
        System.out.println(person1.getSurname());
        System.out.println(person1.getAge());
        context.close();
    }

    public static void test2(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext2.xml");
        Dog myDog = context.getBean("myPet", Dog.class);
        Dog yoorDog = context.getBean("myPet", Dog.class);
        myDog.setName("aaa");
        yoorDog.setName("bbb");
        System.out.println(myDog.getName());
        System.out.println(yoorDog.getName());
        myDog.say();

        context.close();
    }

    public static void test3(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext3.xml");

        Dog2 dog2 = context.getBean("dogBean", Dog2.class);
        dog2.say();
        context.close();
    }

    public static void test4(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext3.xml");

        Person2 person2 = context.getBean("person2Bean", Person2.class);
        System.out.println(person2.getAge());
        person2.callYourPet();
        context.close();
    }

    public static void test5(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        Person3 person3 = context.getBean("person3Bean", Person3.class);
        person3.callYourPet();
        context.close();
    }

    public static void test6(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);
            Pet cat = context.getBean("catBean", Pet.class);
            cat.say();
            context.close();

    }
}
