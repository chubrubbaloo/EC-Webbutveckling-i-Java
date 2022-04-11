package com.example.springboot_projekt_g.security;

import com.example.springboot_projekt_g.entities.AppUser;
import com.example.springboot_projekt_g.repositories.TodoUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    TodoUserRepository todoUserRepository;

    public UserDetailsServiceImpl(TodoUserRepository todoUserRepository) {
        this.todoUserRepository = todoUserRepository;
    }

    @Override // Med hjälp av spring kan vi hämta en användare utifrån användarnamnet sen kolla om
            // username och lösen matchar OM det gör det då loggar vi in annars loggar vi ej in.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = todoUserRepository.findAppUserByUsername(username).orElseThrow();

        return new User(appUser.getUsername(), appUser.getPassword(), List.of()); // Tom lista när det kommer till behandla behörigheter.

    }

}
