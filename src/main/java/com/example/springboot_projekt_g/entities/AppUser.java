package com.example.springboot_projekt_g.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false ,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // Om det skulle finnas en inkonsistens så markerar vi att denna sidan får ge sig, todon blir den ägande sidan.
    @OneToMany(mappedBy = "appUser")
    private Set<Todo> todos; // En kollektion av todos och en referens till alla todos som denna appusern har addat.


    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser(){
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
