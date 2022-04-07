package com.example.springboot_projekt_g.repositories;

import com.example.springboot_projekt_g.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findAppUserByUsername(String username); // Returnera appuser genom username.

}
