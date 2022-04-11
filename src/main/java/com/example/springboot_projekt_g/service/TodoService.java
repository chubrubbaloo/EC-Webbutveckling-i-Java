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

    // Lista av våra todos där vi hittar den kopplade todon till den knutna kontot genom användarnamnet.
    public List<Todo> findByUsername(String username) {
        return toDoRepository.findByAppUserUsername(username);
    }

    public void removeById(int id) {
        toDoRepository.deleteById(id);
    }


    public Todo save(Todo todo) {
        return toDoRepository.save(todo);
    }

    public Todo updateById(int id, Todo changedTodo) {
        Todo existingTodo = toDoRepository.findById(id).orElseThrow();

        if(changedTodo.getCategory() != null)
            existingTodo.setCategory(changedTodo.getCategory());
        if(changedTodo.getTodo() != null)
            existingTodo.setTodo(changedTodo.getTodo());
        if (changedTodo.getPriority()!=null)
            existingTodo.setPriority(changedTodo.getPriority());

        toDoRepository.save(existingTodo);

        return existingTodo;
    }

}

