package com.example.springboot_projekt_g.views;

import com.example.springboot_projekt_g.components.TodoForm;
import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.service.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "/managetodos", layout = AppView.class)
public class ManageTodosView extends VerticalLayout {

    Grid<Todo> grid = new Grid<>(Todo.class, false);
    TodoService todoService;
    TodoForm todoForm;

    public ManageTodosView(TodoService todoService) {
        this.todoService = todoService;
        this.todoForm = new TodoForm(todoService,this);
        setAlignItems(Alignment.CENTER);
        add(new H2("Hantera dina uppgifter"), new Hr());

        grid.setItems(todoService.findAll());
        grid.setWidthFull();

        grid.addComponentColumn(blogPost -> {
            Button button = new Button(new Icon(VaadinIcon.TRASH), evt -> {

                todoService.removeById(blogPost.getId());
                updateItems();

            });

            button.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);

            return button;
        });

        grid.addColumn(Todo::getTitle).setHeader("Titel").setSortable(true).setResizable(true);
        grid.addColumn(Todo::getMessage).setHeader("Uppgift").setSortable(true);
        grid.asSingleSelect().addValueChangeListener(evt -> todoForm.setTodo(evt.getValue()));

        Button button = new Button("Ny uppgift", evt->{
            Dialog modal = new Dialog();
            TodoForm modalForm = new TodoForm(todoService, this);
            modalForm.setTodo(new Todo());
            modal.add(modalForm);
            modal.open();
        });

        HorizontalLayout mainContent = new HorizontalLayout(grid, todoForm);
        mainContent.setSizeFull();

        add(mainContent,button);

    }

    public void updateItems(){
        grid.setItems(todoService.findAll());
    }
}
