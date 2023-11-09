package com.isitc.tp1.service;

import com.isitc.tp1.entities.AppRole;
import com.isitc.tp1.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Initializer {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        Long size = userService.countUser();
        System.out.println(size);
        if (size != 0){
            return;
        }
        AppRole adminRole = new AppRole();
        adminRole.setRolename("ADMIN");
        roleService.createRole(adminRole);

        AppRole userRole = new AppRole();
        userRole.setRolename("USER");
        roleService.createRole(userRole);

        AppUser adminUser = new AppUser();
        adminUser.setUsername("admin");
        adminUser.setPasword(userService.getEncodedPassword("admin"));
        adminUser.setUsername("admin");
        userService.registerNewUser(adminUser);
        List<AppRole> adminRoles = new ArrayList<>();
        adminRoles.add(adminRole);
        adminUser.setAppRoles(adminRoles);
        userService.registerNewUser(adminUser);


        AppUser appUser = new AppUser();
        appUser.setUsername("user");
        appUser.setPasword(userService.getEncodedPassword("user"));
        appUser.setUsername("user");
        userService.registerNewUser(appUser);
        List<AppRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);
        appUser.setAppRoles(userRoles);
        userService.registerNewUser(appUser);

    }
}
