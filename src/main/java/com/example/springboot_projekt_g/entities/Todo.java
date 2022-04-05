package com.example.springboot_projekt_g.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank
    private String category;

    @Column
    @NotBlank
    private String todo;


    public Todo(String category, String todo) {
        this.category = category;
        this.todo = todo;
    }

    public Todo() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getTodo() {
        return todo;
    }

}