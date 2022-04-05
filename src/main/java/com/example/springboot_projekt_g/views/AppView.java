package com.example.springboot_projekt_g.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.router.RouterLink;


// Yttre skalet.

public class AppView extends AppLayout {

    public AppView() {

        HorizontalLayout navbarLayout = new HorizontalLayout();
        navbarLayout.setWidthFull();

        RouterLink todoViewLink = new RouterLink("Se uppgifter", TodoView.class);
        RouterLink manageTodosLink = new RouterLink("Hantera uppgifter", ManageTodosView.class);

        navbarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        navbarLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        navbarLayout.setMargin(true);
        navbarLayout.add(todoViewLink,manageTodosLink);

        addToNavbar(navbarLayout);

    }
}
