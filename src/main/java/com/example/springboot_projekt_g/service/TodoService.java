package com.example.springboot_projekt_g.service;

import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    TodoRepository toDoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.toDoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return toDoRepository.findAll();
    }

    public void removeById(int id) {
        toDoRepository.deleteById(id);
    }


    public Todo save(Todo todo) {
        return toDoRepository.save(todo);
    }

    public Todo updateById(int id, Todo changedTodo) {
        Todo existingTodo = toDoRepository.findById(id).orElseThrow();

        if(changedTodo.getTitle() != null)
            existingTodo.setTitle(changedTodo.getTitle());
        if(changedTodo.getMessage() != null)
            existingTodo.setMessage(changedTodo.getMessage());

        toDoRepository.save(existingTodo);

        return existingTodo;
    }

}

