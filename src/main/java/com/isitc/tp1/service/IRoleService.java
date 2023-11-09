package com.isitc.tp1.service;

import com.isitc.tp1.entities.AppRole;

public interface IRoleService {
    public AppRole findByRoleName(String role);
    public void createRole(AppRole role);
}
