package com.isitc.tp1.service;

import com.isitc.tp1.entities.Acteur;
import com.isitc.tp1.repository.ActeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceActeur implements IServiceActeur{


    @Autowired
    ActeurRepository acteurRepository;
    @Override
    public Acteur createActeur(Acteur f) {
        return acteurRepository.save(f);
    }

    @Override
    public Acteur findActeurById(int id) {
        return acteurRepository.findById(id).get();
    }

    @Override
    public List<Acteur> findAllActeurs() {
        return acteurRepository.findAll();
    }

    @Override
    public Acteur updateActeur(Acteur f) {
        return acteurRepository.save(f);
    }

    @Override
    public void deleteActeur(int id) {
        acteurRepository.deleteById(id);

    }

}
