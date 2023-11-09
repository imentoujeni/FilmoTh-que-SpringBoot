package com.isitc.tp1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Acteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    @ManyToMany(mappedBy = "acteurs", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Film> films;


}
