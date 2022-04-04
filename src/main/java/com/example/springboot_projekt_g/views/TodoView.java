package com.example.springboot_projekt_g.views;

import com.example.springboot_projekt_g.service.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/todoview")
public class TodoView extends VerticalLayout {

    TodoService todoService;

    public TodoView(TodoService todoService) {
        this.todoService = todoService;
        setAlignItems(Alignment.CENTER);

        add(new H1("Hello World!"));

    }
}