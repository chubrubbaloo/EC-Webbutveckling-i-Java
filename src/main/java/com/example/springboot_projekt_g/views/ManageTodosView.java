package com.example.springboot_projekt_g.views;

import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.service.TodoService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "/managetodos", layout = AppView.class)
public class ManageTodosView extends VerticalLayout {

    Grid<Todo> grid = new Grid<>(Todo.class, false);
    TodoService todoService;

    public ManageTodosView(TodoService todoService) {
        this.todoService = todoService;
        setAlignItems(Alignment.CENTER);
        add(new H2("Hantera uppgifter"));

        grid.setItems(todoService.findAll());
        grid.setWidthFull();

        grid.addColumn(Todo::getTitle).setHeader("Titel").setSortable(true).setResizable(true);
        grid.addColumn(Todo::getMessage).setHeader("Uppgift").setSortable(true);

        add(grid);

    }
}
