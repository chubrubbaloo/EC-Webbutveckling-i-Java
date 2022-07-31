package com.example.springboot_projekt_g;

import com.example.springboot_projekt_g.entities.AppUser;
import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.repositories.TodoUserRepository;
import com.example.springboot_projekt_g.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class SpringBootProjektGApplication {


    @Autowired
    PasswordEncoder passwordEncoder;

    /* public SpringBootProjektGApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

     */

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjektGApplication.class, args);
    }

    @Bean
    CommandLineRunner init(TodoRepository todoRepository, TodoUserRepository todoUserRepository) {
        return args -> {

            AppUser haris = new AppUser("Haris", passwordEncoder.encode("pass")); // Krypterar vårat lösen i vår DB så det inte står i klartext.
            AppUser karl = new AppUser("Karl", passwordEncoder.encode("pass"));
            AppUser filip = new AppUser("Filip", passwordEncoder.encode("pass"));

            Todo toDo = new Todo("Studier", "Bygg klart CRUD-projektet.", "Hög");
            Todo secondTodo = new Todo("Mitt livsverk","Bygg klart AI-algoritmen för att ta över världen.","Hög");
            Todo thirdTodo = new Todo("Hälsa","Få in en timmas träning för dagen.","Låg");
            Todo forthTodo = new Todo("Mat", "Laga något som går att äta mer än en gång.", "Hög");
            todoRepository.saveAll(List.of(toDo,secondTodo,thirdTodo));
            todoUserRepository.saveAll(List.of(haris,karl,filip));
            haris.addTodo(toDo);
            haris.addTodo(thirdTodo);
            karl.addTodo(secondTodo);
            filip.addTodo(forthTodo);

            todoRepository.saveAll(List.of(toDo,secondTodo,thirdTodo,forthTodo));
            todoUserRepository.saveAll(List.of(haris,karl, filip));
        };
    }

}
