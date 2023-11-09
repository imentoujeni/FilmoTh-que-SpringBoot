package com.isitc.tp1.service;

import com.isitc.tp1.entities.AppRole;
import com.isitc.tp1.repository.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService{
    @Autowired
    AppRoleRepository appRoleRepository ;
    @Override
    public AppRole findByRoleName(String role) {
        return appRoleRepository.findByRolename(role);
    }

    @Override
    public void createRole(AppRole role) {
        appRoleRepository.save(role);
    }
}
