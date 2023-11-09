package com.isitc.tp1.repository;

import com.isitc.tp1.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
}
