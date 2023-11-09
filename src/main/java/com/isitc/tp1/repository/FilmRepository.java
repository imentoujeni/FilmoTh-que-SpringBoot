package com.isitc.tp1.repository;

import com.isitc.tp1.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    List<Film> findByAnneeparution(int annee) ;

    List<Film> findByCategorieId(int id) ;

}
