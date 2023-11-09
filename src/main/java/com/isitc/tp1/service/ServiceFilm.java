package com.isitc.tp1.service;

import com.isitc.tp1.entities.Film;
import com.isitc.tp1.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceFilm implements IServiceFilm{
    @Autowired
    FilmRepository filmRepository;
    @Override
    public Film createFilm(Film f) {
        return filmRepository.save(f);
    }

    @Override
    public Film findFilmById(int id) {
        return filmRepository.findById(id).get();
    }

    @Override
    public List<Film> findAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film updateFilm(Film f) {
        return filmRepository.save(f);
    }

    @Override
    public void deleteFilm(int id) {
        filmRepository.deleteById(id);
    }

    @Override
    public List<Film> findByAnneeparution(int annee) {
        return filmRepository.findByAnneeparution(annee);
    }

    @Override
    public List<Film> findByCategorieId(int id) {
        return filmRepository.findByCategorieId(id);
    }

    @Override
    public Page<Film> findPaginetedFilms(int pageNum, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() : Sort.by(sortField).descending() ;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
       return  filmRepository.findAll(pageable);
    }
}
