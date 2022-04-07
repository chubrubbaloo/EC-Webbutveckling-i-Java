package com.example.springboot_projekt_g.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("/login")
public class LoginView extends Div implements BeforeEnterObserver {

    LoginOverlay loginMenu = new LoginOverlay(); // Färdig komponent för vårt formulär för att logga in.

    public LoginView() {
        loginMenu.setTitle("Att-göra-appen");
        loginMenu.setDescription("© studiegrupp 9");
        loginMenu.setOpened(true);
        loginMenu.setAction("login");

        add(loginMenu); // Våran loginsida.
    }


    @Override // Kollar innan vi loggar in om queryparametern innehåller nyckeln "error" ifall vi matat in fel lösen.
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")) {
            loginMenu.setError(true);
        }
    }
}
