package com.example.bunshw1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("person3Bean")
public class Person3 {

    private Pet pet;

    private String surname;

    private int age;

//    public Person3() {
//        System.out.println("Person3 bean is created");
//    }

    @Autowired
    public Person3(@Qualifier("dog2Bean") Pet pet) {
        this.pet = pet;
    }

//    @Autowired
//    public void setPet(Pet pet) {
//        this.pet = pet;
//    }

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
