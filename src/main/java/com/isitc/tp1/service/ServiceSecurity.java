package com.isitc.tp1.service;

import com.isitc.tp1.entities.AppRole;
import com.isitc.tp1.entities.AppUser;
import com.isitc.tp1.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceSecurity implements IServiceSecurity{
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }


}
