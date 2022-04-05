package com.example.springboot_projekt_g.views;

import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.service.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route(value = "/managetodos", layout = AppView.class)
public class ManageTodosView extends VerticalLayout {

    Grid<Todo> todoGrid = new Grid<>(Todo.class, false);
    TodoService todoService;

    public ManageTodosView(TodoService todoService) {
        this.todoService = todoService;
        setAlignItems(Alignment.CENTER);
        add(new H2("Hantera uppgifter"));

        todoGrid.setItems(todoService.findAll());
        todoGrid.setWidthFull();

        todoGrid.addComponentColumn(todo -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH), evt -> {

                todoService.removeById(todo.getId());
                todoGrid.setItems(todoService.findAll());
                updateItems();
            });
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);

            return deleteButton;
        });

        todoGrid.addColumn(Todo::getTitle).setHeader("Titel").setSortable(true).setResizable(true);
        todoGrid.addColumn(Todo::getMessage).setHeader("Uppgift").setSortable(true);

        add(todoGrid);

    }

    public void updateItems() {
        todoGrid.setItems(todoService.findAll());
    }
}
