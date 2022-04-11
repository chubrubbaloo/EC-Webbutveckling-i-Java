package com.example.springboot_projekt_g.components;

import com.example.springboot_projekt_g.entities.Todo;
import com.example.springboot_projekt_g.service.TodoService;
import com.example.springboot_projekt_g.views.TodosView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class TodoForm extends FormLayout {

    // Instansfält för vårat formulär.
    TextField category = new TextField("Kategori");
    TextArea todo = new TextArea("Att göra");
    RadioButtonGroup<String> priority = new RadioButtonGroup<>();


    // hjälpgrejjer.
    Binder<Todo> binder = new BeanValidationBinder<>(Todo.class);
    TodoService todoService;
    TodosView todosView;
    Dialog parentWindow;

    public TodoForm(TodoService todoService, TodosView todosView, Dialog parentWindow) {
        this.parentWindow = parentWindow;
        this.todosView = todosView;
        this.todoService = todoService;

        // Binder våra instansfält.
        binder.bindInstanceFields(this);
        binder.setBean(new Todo());

        // skapar knapp
        Button saveButton = new Button("Spara");
        saveButton.addClickListener(evt -> handleSave());

        // stylar priority radioknappar.
        priority.addThemeVariants(RadioGroupVariant.LUMO_HELPER_ABOVE_FIELD);
        priority.setLabel("Prioritetsnivå");
        priority.setItems("Hög","Låg");

        // bygger layout.
        add(category, todo, priority, saveButton);

    }

    // när sparar knappen trycks validerar vi och sparar given data.
    private void handleSave() {

        // validerar Todon. throwar exception ifall valideringen misslyckas.
        Todo todo = binder.validate().getBinder().getBean();

        // Sparar Todon och uppdaterar relavanta fält.
        todoService.save(todo);
        todosView.currentUser.addTodo(todo);
        todosView.updateItems();
        parentWindow.close();
    }
}
