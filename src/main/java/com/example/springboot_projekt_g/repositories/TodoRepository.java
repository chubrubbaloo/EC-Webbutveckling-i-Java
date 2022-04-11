package com.example.springboot_projekt_g.repositories;

import com.example.springboot_projekt_g.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    // Spring hämtar appusern som hör till en to-do och hämtar den med hjälp av username.
    List<Todo> findByAppUserUsername(String username);

}
