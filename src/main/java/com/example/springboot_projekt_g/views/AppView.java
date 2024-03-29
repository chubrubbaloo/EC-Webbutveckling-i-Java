package com.example.springboot_projekt_g.views;

import com.example.springboot_projekt_g.security.LoggedInUser;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.PermitAll;

// Filip lägger till AppView för meny-översikt och plats för login-knapp
@PermitAll
public class AppView extends AppLayout {

    public AppView() {

        HorizontalLayout navbarLayout = new HorizontalLayout();
        Button signOutButton = new Button("Logga ut", evt -> LoggedInUser.logout());

        H1 mainTitle = new H1("Att-Göra-Appen");
        mainTitle.getStyle().set("padding-bottom", "20px");

        navbarLayout.add(
                new DrawerToggle(),
                mainTitle,
                signOutButton);
        navbarLayout.setWidthFull();
        navbarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        navbarLayout.setMargin(true);

        addToNavbar(navbarLayout);

        navbarLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        RouterLink TodosViewLink = new RouterLink("Se lista", TodosView.class);
        RouterLink historyViewLink = new RouterLink("Se historik", HistoryView.class);

        addToDrawer(new VerticalLayout(TodosViewLink, historyViewLink));

    }
}