package com.example.springboot_projekt_g.repositories;

import com.example.springboot_projekt_g.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoUserRepository extends JpaRepository<AppUser, Integer> {

    // Returnera appuser genom username. Optional för att ge möjligheter att hantera om username finns eller inte finns
    // för den som använder våran funktion.
    Optional<AppUser> findAppUserByUsername(String username);

}
