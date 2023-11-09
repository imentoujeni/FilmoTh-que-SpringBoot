package com.isitc.tp1.repository;

import com.isitc.tp1.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByUsername(String username);
}
