package com.example.springboot_projekt_g.views;

import com.example.springboot_projekt_g.service.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/todo")
public class ControllerView extends VerticalLayout {

    TodoService todoService;

    public ControllerView(TodoService todoService) {
        this.todoService = todoService;
        setAlignItems(Alignment.CENTER);

        add(new H1("DIN ATT-GÖRA-LISTA!"), new Hr());

        renderTodos();



    }

    private void updateTodo() {
        this.getChildren()
                .filter(component -> component.getElement().getClassList().contains("todo"))
                .forEach(component -> remove(component));

                renderTodos(); // Varje gång vi tar bort från databasen så renderar vi den specifika todolayoten.
    }

    private void renderTodos() {
        todoService.findAll().forEach(todo -> {
            VerticalLayout todoLayout = new VerticalLayout();

            H3 todoTitle = new H3(todo.getTitle());
            Paragraph todoMessage = new Paragraph(todo.getMessage());

            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH), buttonClickEvent -> {
                todoService.removeById(todo.getId());
                updateTodo();
            });
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_PRIMARY);

            // Håller en to-do.
            todoLayout.setAlignItems(Alignment.CENTER);
            todoLayout.add(todoTitle, todoMessage, deleteButton, new Hr());
            todoLayout.setId(String.valueOf(todo.getId())); // identifierar den specifika todon.
            todoLayout.addClassName("todo");

            add(todoLayout);
        });
    }


}

