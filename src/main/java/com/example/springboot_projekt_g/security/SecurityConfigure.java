package com.example.springboot_projekt_g.security;

import com.example.springboot_projekt_g.entities.AppUser;
import com.example.springboot_projekt_g.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@Configuration // För att spring boot ska plocka upp den (komponent).
public class SecurityConfigure extends VaadinWebSecurityConfigurerAdapter { // Extendar Websecurityconfigadaptern som passar Vaadin-projekt.
// I den ligger färdig config med metoder som vi kan ändra genom att overridea metoderna.

    UserDetailsService userDetailsService;
    public SecurityConfigure(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class); //  http-objektet används för att konfigurerar olika saker. Sätter Loginsidan till våran loginview.
    }


    //
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance()); // Ingen kryptering för tillfället.
    }

    /*

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

     */
}

