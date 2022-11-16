package com.example.springhw2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Book {
    @Value("Война и мир")
    private String name;

    @Value("Л. Н. Толстой")
    private String author;

    @Value("1863")
    private String yearsOfPublication;

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return name;
    }

    public String getYearsOfPublication() {
        return yearsOfPublication;
    }
}
