package com.example.springboot_projekt_g.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    public boolean done;

    @Column
    @NotBlank
    private String category;

    @Column
    @NotBlank
    private String todo;

    @Column
    @NotBlank
    private String priority;

    @ManyToMany(
            mappedBy = "todos",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
            )
    //@JoinColumn(name = "appuser_id") // Våran foreign key som referar till våran primary key i AppUser.
    private Set<AppUser> appUsers; // Referens till den appanvändaren som har skrivit todon.


    public Todo(String category, String todo, String priority) {
        this.category = category;
        this.todo = todo;
        this.appUsers = new HashSet<>();
        this.priority = priority;
        this.done = false;
    }

    public Todo() {
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void addAppUser(AppUser appUser){
        appUser.addTodo(this);
    }

    public void removeAppUser(AppUser appUser) {
        appUser.removeTodo(this);
    }

    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUser(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
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