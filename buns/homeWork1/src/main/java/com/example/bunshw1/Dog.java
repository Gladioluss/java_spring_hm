package com.example.bunshw1;

public class Dog implements Pet{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init() {
        System.out.println("class Dog: Init");
    }

    public void destroy() {
        System.out.println("class Dog: Destroy");
    }
    @Override
    public void say(){
        System.out.println("Bow-Wow");
    }
}
