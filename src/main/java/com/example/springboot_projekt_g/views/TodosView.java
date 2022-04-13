package com.example.springboot_projekt_g.views;

import com.example.springboot_projekt_g.components.TodoForm;
import com.example.springboot_projekt_g.entities.AppUser;
import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.repositories.TodoUserRepository;
import com.example.springboot_projekt_g.security.LoggedInUser;
import com.example.springboot_projekt_g.service.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "/todo", layout = AppView.class)
public class TodosView extends VerticalLayout {

    TodoService todoService;
    public TodoUserRepository todoUserRepository;

    public AppUser currentUser;
    Grid<Todo> grid = new Grid<>(Todo.class, false);

    //available windows:
    TodoForm todoForm;

    public TodosView(TodoService todoService, TodoUserRepository todoUserRepository) {
        this.todoUserRepository = todoUserRepository;
        this.todoService = todoService;
        this.currentUser = todoUserRepository.findAppUserByUsername(LoggedInUser.getLoggedInUserName()).orElseThrow();

        Dialog newTodoWindow = new Dialog();
        todoForm = new TodoForm(todoService, this, newTodoWindow);
        newTodoWindow.add(todoForm);


        HorizontalLayout headerContent = new HorizontalLayout();
        headerContent.setWidthFull();
        headerContent.setJustifyContentMode(JustifyContentMode.BETWEEN);
        H3 todoTitle = new H3("Min lista");
        H3 loggedInAs = new H3("Inloggad som " + currentUser.getUsername());
        todoTitle.getStyle().set("margin-left","20px");
        loggedInAs.getStyle().set("margin-right","20px");

        headerContent.add(todoTitle,loggedInAs);
        add(headerContent);

        updateItems();
        grid.setWidthFull();

        // Filip lägger till  (med hjälp av Haris & Viktor) 2022-04-12
        grid.addComponentColumn(todo -> {
            Checkbox checkbox = new Checkbox();
            checkbox.setValue(todo.isDone());
            checkbox.addValueChangeListener(event -> {
                todo.setDone(event.getValue());
                todoService.save(todo);
                updateItems();
            });
            return checkbox;
        }).setWidth("90px").setHeader("Check").setResizable(true).setFlexGrow(0);

        grid.addComponentColumn(this::deleteButton).setWidth("90px").setHeader("Radera").setResizable(true).setFlexGrow(0);
        grid.addComponentColumn(this::editButton).setWidth("90px").setHeader("Redigera").setResizable(true).setFlexGrow(0);
        grid.addColumn(Todo::getCategory).setWidth("150px").setHeader("Kategori").setResizable(true).setFlexGrow(0);
        grid.addColumn(Todo::getTodo).setHeader("Att göra").setResizable(true).setFlexGrow(3);
        grid.addColumn(Todo::getPriority).setHeader("Prioritetsnivå").setSortable(true).setWidth("150px").setFlexGrow(0);

        Button addButton = addButton();

        VerticalLayout mainContent = new VerticalLayout(grid, new Hr());
        mainContent.setSizeFull();

        add(mainContent,addButton);

    }

    // Uppdaterar våra todos till den inloggade användaren.
    public void updateItems(){
        currentUser = todoUserRepository.findAppUserByUsername(LoggedInUser.getLoggedInUserName()).orElseThrow();
        grid.setItems(currentUser.getTodos()
                .stream()
                .filter(todo -> !todo.isDone())
                .toList());
    }

    private Button deleteButton(Todo todo){
        Button deleteButton = new Button(new Icon(VaadinIcon.TRASH), evt -> {
            currentUser.removeTodo(todo);
            todoUserRepository.save(currentUser);
            updateItems();
        });

        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        return deleteButton;
    }

    private Button editButton(Todo todo){
        Button editButton = new Button(new Icon(VaadinIcon.PENCIL), evt -> {
            todoForm.setTodo(todo);
            todoForm.open();
        });

        editButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        return editButton;
    }

    private Button addButton(){
        Button addButton = new Button("Ny uppgift", evt -> {
            todoForm.setTodo(new Todo());
            todoForm.open();
        });

        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_LARGE);
        return addButton;
    }
}
