package com.example.springboot_projekt_g.views;

import com.example.springboot_projekt_g.entities.AppUser;
import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.repositories.TodoUserRepository;
import com.example.springboot_projekt_g.security.LoggedInUser;
import com.example.springboot_projekt_g.service.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;


@PermitAll
@Route(value = "/managehistory", layout = AppView.class)
public class HistoryView extends VerticalLayout {
    Grid<Todo> grid = new Grid<>(Todo.class, false);
    TodoService todoService;
    TodoUserRepository todoUserRepository;
    public AppUser currentUser;

    public HistoryView(TodoService todoService, TodoUserRepository todoUserRepository) {
        this.todoUserRepository = todoUserRepository;
        this.todoService = todoService;
        this.currentUser = todoUserRepository.findAppUserByUsername(LoggedInUser.getLoggedInUserName()).orElseThrow();

        HorizontalLayout headerContent = new HorizontalLayout();
        headerContent.setWidthFull();
        headerContent.setJustifyContentMode(JustifyContentMode.BETWEEN);
        H3 historyTitle = new H3("Min historik");
        H3 loggedInAs = new H3("Inloggad som " + currentUser.getUsername());
        historyTitle.getStyle().set("margin-left","20px");
        loggedInAs.getStyle().set("margin-right","20px");

        headerContent.add(historyTitle,loggedInAs);
        add(headerContent);

        updateItems();
        grid.setWidthFull();

        // Filip lägger till historik-checkbox (med hjälp av Haris & Viktor) 2022-04-12
        // Todo flyttas till todo när checkbox checkats ur.
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

        grid.addComponentColumn(this::deleteButtonEvent);
        grid.addColumn(Todo::getTimeStampClean).setHeader("Utförd").setResizable(true);
        grid.addColumn(Todo::getCategory).setHeader("Kategori").setResizable(true);
        grid.addColumn(Todo::getTodo).setHeader("Att göra").setResizable(true);
        grid.addColumn(Todo::getPriority).setHeader("Prioritetsnivå").setSortable(true);

        VerticalLayout mainContent = new VerticalLayout(grid);
        mainContent.setSizeFull();

        add(mainContent);

    }

    // Uppdaterar våra todos till den inloggade användaren.
    // Checkade todos visas
    public void updateItems() {
        grid.setItems(currentUser.getTodos()
                .stream()
                .filter(Todo::isDone)
                .toList());
    }

    // Tar bort todos
    private Button deleteButtonEvent(Todo todo) {
        Button deleteButton = new Button(new Icon(VaadinIcon.TRASH), evt -> {
            currentUser.removeTodo(todo);
            todoUserRepository.save(currentUser);
            updateItems();

        });
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        return deleteButton;
    }
}
