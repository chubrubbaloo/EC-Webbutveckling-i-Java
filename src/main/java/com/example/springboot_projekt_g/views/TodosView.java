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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/todo")
public class TodosView extends VerticalLayout {

    Grid<Todo> grid = new Grid<>(Todo.class, false);
    TodoService todoService;
    TodoForm todoForm;

    public TodosView(TodoService todoService) {
        this.todoService = todoService;
        this.todoForm = new TodoForm(todoService,this);
        setAlignItems(Alignment.CENTER);
        add(new H2("Välkommen till din att-göra-lista"), new Hr());

        grid.setItems(todoService.findAll());
        grid.setWidthFull();

        grid.addComponentColumn(blogPost -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH), evt -> {

                todoService.removeById(blogPost.getId());
                updateItems();

            });
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);

            return deleteButton;
        });

        grid.addColumn(Todo::getTitle).setHeader("Titel").setResizable(true).setSortable(true);
        grid.addColumn(Todo::getMessage).setHeader("Att göra").setSortable(true);
        grid.asSingleSelect().addValueChangeListener(evt -> todoForm.setTodo(evt.getValue()));

        Button addButton = new Button("Ny uppgift", evt->{
            Dialog modal = new Dialog();
            TodoForm modalForm = new TodoForm(todoService, this);
            modalForm.setTodo(new Todo());
            modal.add(modalForm);
            modal.open();
        });
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_LARGE);

        VerticalLayout mainContent = new VerticalLayout(grid, todoForm,new Hr());
        mainContent.setSizeFull();

        add(mainContent,addButton);

    }

    public void updateItems(){
        grid.setItems(todoService.findAll());
    }
}
