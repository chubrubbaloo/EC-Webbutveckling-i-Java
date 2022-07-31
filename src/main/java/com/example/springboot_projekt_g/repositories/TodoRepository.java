package com.example.springboot_projekt_g.repositories;

import com.example.springboot_projekt_g.entities.AppUser;
import com.example.springboot_projekt_g.entities.Todo;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    // Spring hämtar lista på todos som hör till en todoUser.
    List<Todo> findByAppUsers(AppUser appUser);

}
