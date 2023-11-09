package com.isitc.tp1.service;

import com.isitc.tp1.entities.Film;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IServiceFilm {

    public Film createFilm(Film f) ;
    public Film findFilmById(int id);
    public List<Film> findAllFilms();
    public Film updateFilm(Film f);
    public void deleteFilm(int id);
    List<Film> findByAnneeparution(int annee);
    List<Film> findByCategorieId(int id);
    Page<Film> findPaginetedFilms(int pageNum, int pageSize, String sortField, String sortDir);
}
