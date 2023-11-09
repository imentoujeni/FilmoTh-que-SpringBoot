package com.isitc.tp1.service;

import com.isitc.tp1.entities.AppRole;
import com.isitc.tp1.entities.AppUser;
import com.isitc.tp1.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService{
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser registerNewUser(AppUser user) {
        return appUserRepository.save(user);
    }

    @Override
    public Long countUser() {
        return appUserRepository.count();
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
