package com.example.springhw2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringHw2Application {

    public static void main(String[] args) {
//        SpringApplication.run(SpringHw2Application.class, args);
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        UniLibrary uniLibrary = context.getBean("uniLibrary", UniLibrary.class);
        Book book = context.getBean("book", Book.class);
//        uniLibrary.getBook();
        uniLibrary.addBook("Данил", book);
        String bookName = uniLibrary.returnBook();
//        System.out.println(bookName);
        context.close();
    }
}
