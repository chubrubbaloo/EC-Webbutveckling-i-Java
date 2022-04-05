package com.example.springboot_projekt_g.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "/managetodos", layout = AppView.class)
public class ManageTodosView extends VerticalLayout {

    public ManageTodosView() {
        setAlignItems(Alignment.CENTER);
        add(new H1("Hantera dina uppgifter"));
    }
}
