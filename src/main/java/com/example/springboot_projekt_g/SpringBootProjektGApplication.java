package com.example.springboot_projekt_g;

import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.repositories.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootProjektGApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjektGApplication.class, args);
    }
    @Bean
    CommandLineRunner init(TodoRepository todoRepository) {
        return args -> {

            Todo toDo = new Todo("Studier", "Lär dig Spring Boot & Thymeleaf.");
            Todo secondTodo = new Todo("Hälsa","Få in 30 minuters motion för dagen.");
            todoRepository.save(toDo);
            todoRepository.save(secondTodo);

        };
    }

}
