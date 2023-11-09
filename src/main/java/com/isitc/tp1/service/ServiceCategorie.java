package com.isitc.tp1.service;


import com.isitc.tp1.entities.Categorie;
import com.isitc.tp1.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategorie implements IServiceCategorie{

    @Autowired
    CategorieRepository cr;

    @Override
    public Categorie createCategorie(Categorie c) {
        return cr.save(c);
    }

    @Override
    public Categorie findCategorieById(int id) {
        return cr.findById(id).get();
    }

    @Override
    public List<Categorie> findAllCategories() {
        // TODO Auto-generated method stub
        return cr.findAll();
    }

    @Override
    public Categorie updateCategorie(Categorie f) {
        // TODO Auto-generated method stub
        return cr.save(f);
    }

    @Override
    public void deleteCategorie(int id) {
        // TODO Auto-generated method stub
        cr.deleteById(id);
    }

}