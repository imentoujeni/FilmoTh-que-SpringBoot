package com.isitc.tp1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rolename;
    private String description;
    @ManyToMany (mappedBy = "appRoles")
    private List<AppUser> appUsers;
}
