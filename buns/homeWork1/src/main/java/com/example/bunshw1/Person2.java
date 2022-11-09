package com.example.bunshw1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("person2Bean")
public class Person2 {
    @Autowired
    @Qualifier("dog2Bean")
    private Pet pet;
    @Value("Emurashin")
    private String surname;
//    @Value("${person.age}")
    private int age;

    public Person2() {
        System.out.println("Person2 bean is created");
    }

//    @Autowired
//    public Person2(Pet pet) {
//        this.pet = pet;
//    }

//    @Autowired
//    public void setPet(Pet pet) {
//        this.pet = pet;
//    }

    public Pet getPet() {
        return pet;
    }
    public void callYourPet() {
        System.out.println("Hello, my lovely pet!");
        pet.say();
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
