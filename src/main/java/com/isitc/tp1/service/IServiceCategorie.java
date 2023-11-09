package com.isitc.tp1.service;

import com.isitc.tp1.entities.Categorie;

import java.util.List;

public interface IServiceCategorie {

    public Categorie createCategorie(Categorie c)  ;
    public Categorie findCategorieById(int id) ;
    public List<Categorie> findAllCategories();
    public Categorie updateCategorie(Categorie f) ;
    public void deleteCategorie (int id);

}