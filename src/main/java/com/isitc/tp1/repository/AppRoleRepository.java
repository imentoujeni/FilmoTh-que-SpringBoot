package com.isitc.tp1.repository;

import com.isitc.tp1.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {
    AppRole findByRolename(String rolename);
}
