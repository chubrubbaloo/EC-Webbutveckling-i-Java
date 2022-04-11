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
    Button saveButton = new Button("Spara");

    Binder<Todo> binder = new BeanValidationBinder<>(Todo.class);
    TodoService todoService;
    TodosView todosView;

    public TodoForm(TodoService todoService, TodosView todosView) {
        this.todosView = todosView;
        this.todoService = todoService;
        // Binder våra instansfält.
        binder.bindInstanceFields(this);

        saveButton.addClickListener(evt -> handleSave());

        priority.addThemeVariants(RadioGroupVariant.LUMO_HELPER_ABOVE_FIELD);
        priority.setLabel("Prioritetsnivå");
        priority.setItems("Hög","Låg");

        add(category, todo, priority, saveButton);

        setVisible(false);

    }

    // Sparar våran todos genom att validera med våran bean och sedan uppdatera genom det knutna id:et till todon.
    // Efter det så stänger vi våran modal.
    private void handleSave() {
        Todo todo = binder.validate().getBinder().getBean();
        if (todo.getId() == 0) {
            todoService.save(todo);
        } else {
            todoService.updateById(todo.getId(), todo);
        }
        setTodo(null);
        todosView.updateItems();

        this.getParent().ifPresent(component -> {
            if (component instanceof Dialog) {
                ((Dialog) component).close();
            }
        });

    }

    // Sätter våran nya to-do med hjälp av bean sålänge todon inte är tom annars så kommer den inte att visas.
    public void setTodo(Todo todo) {
        if (todo != null) {
            binder.setBean(todo);
            setVisible(true);
        } else {
            setVisible(false);
        }
    }


}
