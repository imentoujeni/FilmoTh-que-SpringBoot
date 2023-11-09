package com.isitc.tp1.controllers;

import com.isitc.tp1.entities.Acteur;
import com.isitc.tp1.service.IServiceActeur;
import com.isitc.tp1.service.IServiceCategorie;
import com.isitc.tp1.service.IServiceFilm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/acteur/")
public class acteurController {
    @Autowired
    IServiceActeur isa;

    @Autowired
    IServiceFilm isf;

    @GetMapping("all")
    public String getAllFilms(Model model){
        model.addAttribute("acteurs", isa.findAllActeurs());
        return "acteur/affiche";
    }
    @GetMapping("new")
    public String afficheNew(Model model){
        return "acteur/addActeur";
    }
    @GetMapping("modifier/{id}")
    public String afficheModifier(Model model,@PathVariable int id){
        model.addAttribute("acteur",isa.findActeurById(id) );
        return "update";
    }
    @PostMapping("add")
    public String add(Acteur f) {
        System.out.println(f);
        isa.createActeur(f);
        return "redirect:/acteur/all";
    }
    @PostMapping("mod")
    public String mod(Acteur f) {
        isa.updateActeur(f);
        return "redirect:/acteur/all";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        isa.deleteActeur(id);
        return "redirect:/acteur/all";
    }
}
