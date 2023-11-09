package com.isitc.tp1.service;

import com.isitc.tp1.entities.AppUser;

import java.util.List;

public interface IUserService {
    public AppUser registerNewUser(AppUser user);
    public Long countUser();
}
