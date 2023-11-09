package com.isitc.tp1.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import com.isitc.tp1.entities.Categorie;
import com.isitc.tp1.entities.Film;
import com.isitc.tp1.service.IServiceActeur;
import com.isitc.tp1.service.IServiceCategorie;
import com.isitc.tp1.service.IServiceFilm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/film/")
public class filmController {

    @Autowired
    IServiceFilm isf;
    @Autowired
    IServiceCategorie isc;
    @Autowired
    IServiceActeur isa;

    private String uploadDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\photos";
    @GetMapping("all")
    public String getAllFilms(Model model){
//        model.addAttribute("films", isf.findAllFilms());
        model.addAttribute("categories",isc.findAllCategories());
        return findPagineted(1, "title", "asc", model);
    }
    @GetMapping("new")
    public String afficheNew(Model model){
        model.addAttribute("categories",isc.findAllCategories() );
        model.addAttribute("acteurs",isa.findAllActeurs() );

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        model.addAttribute("currentYear", currentYear);
        return "addFilm";
    }
    @GetMapping("/cherche")
    public String chercheFilmByAnnee(@RequestParam("annee") String annee, Model m) {
        if (annee == ""){
            return "redirect:/film/all";
        }

        System.out.println(annee);
        List<Film> liste = isf.findByAnneeparution(Integer.parseInt(annee));
        m.addAttribute("films", liste);
        m.addAttribute("annee", annee);
        return "affiche";

    }
    @GetMapping("/filterbycategory")
    public String filterByCategory(Categorie categorie, Model m) {
        if (categorie.getId() == 0){
            return "redirect:/film/all";
        }
        List<Film> liste = isf.findByCategorieId(categorie.getId());
        m.addAttribute("films", liste);
        m.addAttribute("categorieId", categorie.getId());
        m.addAttribute("categories",isc.findAllCategories());
        return "affiche";

    }


    @GetMapping("modifier/{id}")
    public String afficheModifier(Model model,@PathVariable int id){
        model.addAttribute("categories",isc.findAllCategories() );
        model.addAttribute("acteurs",isa.findAllActeurs() );
        model.addAttribute("film",isf.findFilmById(id) );
        return "update";
    }

    @GetMapping("details/{id}")
    public String afficheDetails(Model model,@PathVariable int id){
        model.addAttribute("film",isf.findFilmById(id) );
        return "detail";
    }
    @PostMapping("add")
    public String add(Model model, @Valid Film f, @RequestParam("file") MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
        try {
            try {
                Files.write(fileNameAndPath, multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            f.setPhoto(fileName);
            isf.createFilm(f);
        }catch (DataIntegrityViolationException e) {
            model.addAttribute("msgerror","d’erreur sous le champ titre");
            model.addAttribute("categories",isc.findAllCategories() );
            model.addAttribute("acteurs",isa.findAllActeurs() );
            model.addAttribute("title",f.getTitle());
            model.addAttribute("acteur",f.getActeurs());
            model.addAttribute("categorie",f.getCategorie());
            model.addAttribute("anneeparution",f.getAnneeparution());
            model.addAttribute("description",f.getDescription());
            return "addFilm";
        }

        return "redirect:/film/all";
    }
    @PostMapping("mod")
    public String mod(Film f, @RequestParam("file") MultipartFile multipartFile){


        System.out.println(f.getPhoto());

        String fileName = multipartFile.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, fileName);

        if (!((f.getPhoto() != "" || f.getPhoto() != null) && (fileName == "" || fileName == null))){

            try {
                Files.write(fileNameAndPath, multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            f.setPhoto(fileName);


        }
        isf.updateFilm(f);
        //f.setPhoto(fileName);
        return "redirect:/film/all";
    }
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        isf.deleteFilm(id);
        return "redirect:/film/all";
    }

    @GetMapping("page/{pageNum}")
    public String findPagineted(@PathVariable int pageNum, @RequestParam String sortField, @RequestParam String sortDir,Model model){
        int pageSize = 4;
        Page<Film> page = isf.findPaginetedFilms(pageNum, pageSize, sortField, sortDir);
        model.addAttribute("films", page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", pageNum);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc": "asc");
        model.addAttribute("categories",isc.findAllCategories());
        return "affiche";

    }
}
