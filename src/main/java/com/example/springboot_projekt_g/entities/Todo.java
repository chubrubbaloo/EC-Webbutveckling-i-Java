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

    @ManyToOne
    @JoinColumn(name = "appuser_id") // Våran foreign key som referar till våran primary key i AppUser.
    private AppUser appUser; // Referens till den appanvändaren som har skrivit todon.


    public Todo(String category, String todo, AppUser appUser) {
        this.category = category;
        this.todo = todo;
        this.appUser = appUser;
    }

    public Todo() {
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
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