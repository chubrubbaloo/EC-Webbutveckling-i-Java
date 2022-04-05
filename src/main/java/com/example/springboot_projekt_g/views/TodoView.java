package com.example.springboot_projekt_g.views;

import com.example.springboot_projekt_g.service.TodoService;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
// Main-sidan.
@Route(value = "/todos", layout = AppView.class)
public class TodoView extends VerticalLayout {

    public TodoView(TodoService todoService) {
        setAlignItems(Alignment.CENTER);
        add(new H2("Dina uppgifter"), new Hr());

        todoService.findAll().forEach(todo -> {

            H3 todoTitle = new H3(todo.getTitle());
            Paragraph todoMessage = new Paragraph(todo.getMessage());
            add(todoTitle,todoMessage,new Hr());
        });
    }

}
