package com.example.springboot_projekt_g.views;

import com.example.springboot_projekt_g.components.TodoForm;
import com.example.springboot_projekt_g.entities.AppUser;
import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.repositories.TodoRepository;
import com.example.springboot_projekt_g.repositories.TodoUserRepository;
import com.example.springboot_projekt_g.security.LoggedInUser;
import com.example.springboot_projekt_g.service.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;
import java.util.Set;

@PermitAll
@Route("/todo")
public class TodosView extends VerticalLayout {

    Grid<Todo> grid = new Grid<>(Todo.class, false);
    TodoService todoService;
    TodoUserRepository todoUserRepository;
    public AppUser currentUser;

    public TodosView(TodoService todoService, TodoUserRepository todoUserRepository) {
        this.todoUserRepository = todoUserRepository;
        this.todoService = todoService;
        this.currentUser = todoUserRepository.findAppUserByUsername(LoggedInUser.getLoggedInUserName()).orElseThrow();


        HorizontalLayout headerContent = new HorizontalLayout();
        H3 mainTitle = new H3("Att-Göra-Appen");
        Button signOutButton = new Button("Logga ut", evt -> LoggedInUser.logout());

        headerContent.setWidthFull();
        headerContent.setJustifyContentMode(JustifyContentMode.BETWEEN);
        headerContent.setAlignItems(Alignment.CENTER);

        headerContent.add(mainTitle,signOutButton);
        add(headerContent,new Hr());

        updateItems();
        grid.setWidthFull();

        grid.addComponentColumn(this::deleteButtonEvent);
        grid.addColumn(Todo::getCategory).setHeader("Kategori").setResizable(true);
        grid.addColumn(Todo::getTodo).setHeader("Att göra").setResizable(true);
        grid.addColumn(Todo::getPriority).setHeader("Prioritetsnivå").setSortable(true);

        Button addButton = new Button("Ny uppgift", evt->{
            Dialog newTodoWindow = new Dialog();
            newTodoWindow.add(new TodoForm(todoService, this, newTodoWindow));
            newTodoWindow.open();
        });
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_LARGE);

        VerticalLayout mainContent = new VerticalLayout(grid, new Hr());
        mainContent.setSizeFull();

        add(mainContent,addButton);

    }

    // Uppdaterar våra todos till den inloggade användaren.
    public void updateItems(){
        grid.setItems(currentUser.getTodos());
    }

    private Button deleteButtonEvent(Todo todo){
        Button deleteButton = new Button(new Icon(VaadinIcon.TRASH), evt -> {
            currentUser.removeTodo(todo);
            todoUserRepository.save(currentUser);
            updateItems();

        });
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        return deleteButton;
    }
}
