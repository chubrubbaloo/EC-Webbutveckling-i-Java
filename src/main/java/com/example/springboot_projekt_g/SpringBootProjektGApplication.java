package com.example.springboot_projekt_g;

import com.example.springboot_projekt_g.entities.AppUser;
import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.repositories.TodoUserRepository;
import com.example.springboot_projekt_g.repositories.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class SpringBootProjektGApplication {


    PasswordEncoder passwordEncoder;
    public SpringBootProjektGApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjektGApplication.class, args);
    }
    @Bean
    CommandLineRunner init(TodoRepository todoRepository, TodoUserRepository todoUserRepository) {
        return args -> {

            AppUser haris = new AppUser("Haris", passwordEncoder.encode("pass")); // Krypterar vårat lösen i vår DB så det inte står i klartext.
            AppUser karl = new AppUser("Karl", passwordEncoder.encode("pass"));
            todoUserRepository.saveAll(List.of(haris,karl));

            Todo toDo = new Todo("Studier", "Bygg klart CRUD-projektet.",haris,"Hög");
            Todo secondTodo = new Todo("Mitt livsverk","Bygg klart AI-algoritmen för att ta över världen.",karl,"Hög");
            Todo thirdTodo = new Todo("Hälsa","Få in en timmas träning för dagen.",haris,"Låg");
            todoRepository.saveAll(List.of(toDo,secondTodo,thirdTodo));
        };
    }

}
