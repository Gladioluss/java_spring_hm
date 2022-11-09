package com.example.bunshw1;

import org.springframework.context.annotation.*;

@Configuration
//@ComponentScan("com.example.bunshw1")
@PropertySource("classpath:application.properties")
public class MyConfig {

    @Bean
    @Scope("prototype")
    public Pet catBean(){
        return new Cat();
    }

    @Bean
    @Scope("prototype")
    public Person3 person3Bean(){
        return new Person3(catBean());
    }
}
