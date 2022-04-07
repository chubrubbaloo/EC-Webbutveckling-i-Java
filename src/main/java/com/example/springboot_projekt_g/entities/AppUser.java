package com.example.springboot_projekt_g.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AppUser {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false ,unique = true)
    private String username;

    @OneToMany(mappedBy = "appUser") // Om det skulle finnas en inkonsistens så markerar vi att denna sidan får ge sig
                                    // todon blir den ägande sidan.
    @JsonIgnoreProperties("appUser")
    private Set<Todo> todos; // En kollektion av todos och en referens till alla todos som denna appusern har addat.


    public AppUser(String username) {
        this.username = username;
    }

    public AppUser(){
    }

    public Set<Todo> getTodos() {
        return todos;
    }

    public void setTodos(Set<Todo> todos) {
        this.todos = todos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
