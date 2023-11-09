package com.isitc.tp1.service;

import com.isitc.tp1.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ServiceSecurity serviceSecurity;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = serviceSecurity.loadUserByUserName(username); Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        appUser.getAppRoles().forEach(role->{
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRolename());
            authorities.add(simpleGrantedAuthority);
        });
        User user = new User (appUser.getUsername(), appUser.getPasword(), authorities);
        return user;
    }
}
