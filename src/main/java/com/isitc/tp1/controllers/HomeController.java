package com.isitc.tp1.controllers;

import com.isitc.tp1.entities.Categorie;
import com.isitc.tp1.service.IServiceCategorie;
import com.isitc.tp1.service.IServiceFilm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    IServiceCategorie isc;
    @Autowired
    IServiceFilm isf;

    @GetMapping("/")
    public String getAllFilms(Model model){
        model.addAttribute("categories",isc.findAllCategories());
        return "index";
    }
    @GetMapping("/cat/{id}")
    public String getAllFilmsByCategory(Model model, @PathVariable int id){
        Categorie categorie = isc.findCategorieById(id);
        model.addAttribute("nomCategorie", categorie.getNom());
        model.addAttribute("films", categorie.getFilms());
        return "detailsFilms";
    }
    @GetMapping("/details/{id}")
    public String afficheDetails(Model model,@PathVariable int id){
        model.addAttribute("film",isf.findFilmById(id) );
        return "detail";
    }

    @GetMapping("/forbidden")
    public String forbidden() {
        return "forbidden";
    }
}
