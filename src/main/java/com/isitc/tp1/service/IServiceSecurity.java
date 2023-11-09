package com.isitc.tp1.service;

import com.isitc.tp1.entities.AppUser;

public interface IServiceSecurity {

    public AppUser loadUserByUserName(String username);

}
