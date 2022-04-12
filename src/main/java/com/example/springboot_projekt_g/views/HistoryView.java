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
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Route(value = "/managehistory")
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
    H3 mainTitle = new H3("Att-Göra-Appen");
    Button signOutButton = new Button("Logga ut", evt -> LoggedInUser.logout());

        headerContent.setWidthFull();
        headerContent.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        headerContent.setAlignItems(FlexComponent.Alignment.CENTER);

        headerContent.add(mainTitle,signOutButton);
    add(headerContent,new Hr());

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
    }).setWidth("90px").setHeader("Done").setResizable(true).setFlexGrow(0);

        grid.addComponentColumn(this::deleteButtonEvent);
        grid.addColumn(Todo::getCategory).setHeader("Kategori").setResizable(true);
        grid.addColumn(Todo::getTodo).setHeader("Att göra").setResizable(true);
        grid.addColumn(Todo::getPriority).setHeader("Prioritetsnivå").setSortable(true);

    VerticalLayout mainContent = new VerticalLayout(grid);
        mainContent.setSizeFull();

    add(mainContent);

}

    // Uppdaterar våra todos till den inloggade användaren.
    public void updateItems(){
        // Ändrad av Filip 2022-04-12
        grid.setItems(currentUser.getTodos()
                .stream()
                .filter(Todo::isDone)
                .toList());
    }

    private Button deleteButtonEvent(Todo todo){
        Button deleteButton = new Button(new Icon(VaadinIcon.TRASH), evt -> {
            currentUser.removeTodo(todo);
            updateItems();

        });
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        return deleteButton;
    }
}
