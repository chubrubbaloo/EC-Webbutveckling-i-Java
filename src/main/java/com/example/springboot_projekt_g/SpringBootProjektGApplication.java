package com.example.springboot_projekt_g;

import com.example.springboot_projekt_g.entities.AppUser;
import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.repositories.AppUserRepository;
import com.example.springboot_projekt_g.repositories.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBootProjektGApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjektGApplication.class, args);
    }
    @Bean
    CommandLineRunner init(TodoRepository todoRepository, AppUserRepository appUserRepository) {
        return args -> {

            AppUser haris = new AppUser("Haris","pass");
            appUserRepository.save(haris);


            Todo toDo = new Todo("Studier", "Bygg klart CRUD-projektet.",haris);
            Todo secondTodo = new Todo("Hälsa","Få in 45 minuters sol för dagen.",haris);
            todoRepository.save(toDo);
            todoRepository.save(secondTodo);

        };
    }

}
