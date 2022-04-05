package com.example.springboot_projekt_g.components;

import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.service.TodoService;
import com.example.springboot_projekt_g.views.ManageTodosView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class TodoForm extends FormLayout {

    TextField title = new TextField("Redigera titel");
    TextArea message = new TextArea("Redigera uppgift");
    Button saveButton = new Button("Spara");

    Binder<Todo> binder = new BeanValidationBinder<>(Todo.class);
    TodoService todoService;
    ManageTodosView manageTodosView;

    public TodoForm(TodoService todoService, ManageTodosView manageTodosView){
        this.manageTodosView = manageTodosView;
        this.todoService = todoService;
        binder.bindInstanceFields(this);

        saveButton.addClickListener(evt -> handleSave());

        add(title, message, saveButton);

        setVisible(false);

    }

    private void handleSave(){
        Todo todo = binder.validate().getBinder().getBean();
        if(todo.getId() == 0){
            todoService.save(todo);
        } else {
            todoService.updateById(todo.getId(), todo);
        }
        setBlogPost(null);
        manageTodosView.updateItems();

    }

    public void setBlogPost(Todo todo){
        if(todo != null){
            binder.setBean(todo);
            setVisible(true);
        } else {
            setVisible(false);
        }
    }




}
