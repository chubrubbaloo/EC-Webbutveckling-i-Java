package com.example.springboot_projekt_g.security;

import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LoggedInUser {

    // Får ut användarnamnet.
    public static String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    // Genom SecurityContextLogoutHandler så får vi ut en metod som heter logout där vi tar in en request både
    // via vaadin och http sedan en respons och autentisering där vi satt båda till null.
    public static void logout(){
        new SecurityContextLogoutHandler()
                .logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
    }
}
