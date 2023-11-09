package com.isitc.tp1.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String title;
    @Min(value = 10, message = "Description must be at least 10 characters long")
    private String description;
    private int anneeparution;
    @ManyToOne
    private Categorie categorie;
    @ManyToMany
    private List<Acteur> acteurs;
    private String photo;
}
