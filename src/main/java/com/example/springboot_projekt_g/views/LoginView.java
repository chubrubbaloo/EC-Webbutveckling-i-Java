package com.example.springboot_projekt_g.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;


@Route("/login")
public class LoginView extends Div implements BeforeEnterObserver {

    LoginOverlay loginMenu = new LoginOverlay();

    public LoginView() {
        loginMenu.setTitle("Att-Göra-Appen");
        loginMenu.setDescription("© studiegrupp 9");
        loginMenu.setOpened(true);
        loginMenu.setAction("login");
        add(loginMenu);
    }

    @Override // Kollar innan vi loggar in om queryparametern innehåller nyckeln "error" ifall vi matat in fel lösen.
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")) {
            loginMenu.setError(true);
        } else {
            UI.getCurrent().navigate("/todo");
        }
    }
}
