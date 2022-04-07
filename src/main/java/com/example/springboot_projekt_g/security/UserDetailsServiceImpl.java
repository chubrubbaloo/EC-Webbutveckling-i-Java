package com.example.springboot_projekt_g.security;

import com.example.springboot_projekt_g.entities.AppUser;
import com.example.springboot_projekt_g.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    AppUserRepository appUserRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override // Spring kan använda denna för att hämta en användare utifrån användarnamnet sen kolla om username och lösen matchar då loggar vi in annars ej.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findAppUserByUsername(username).orElseThrow();

        return new User(appUser.getUsername(), appUser.getPassword(), List.of()); // Tom lista när det kommer till behandla behörigheter.

    }

}
