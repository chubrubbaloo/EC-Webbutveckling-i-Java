package com.example.springboot_projekt_g;

import com.example.springboot_projekt_g.entities.AppUser;
import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.repositories.AppUserRepository;
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

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjektGApplication.class, args);
    }
    @Bean
    CommandLineRunner init(TodoRepository todoRepository, AppUserRepository appUserRepository) {
        return args -> {

            AppUser haris = new AppUser("Haris", passwordEncoder.encode("pass")); // Krypterar vårat lösen i vår DB så det inte står i klartext.
            AppUser karl = new AppUser("Karl", passwordEncoder.encode("pass"));
            appUserRepository.save(haris);
            appUserRepository.save(karl);


            Todo toDo = new Todo("Studier", "Bygg klart CRUD-projektet.",haris,"2");
            Todo secondTodo = new Todo("Hälsa","Få in 45 minuters sol för dagen.",karl,"1");
            todoRepository.save(toDo);
            todoRepository.save(secondTodo);

        };
    }

}
