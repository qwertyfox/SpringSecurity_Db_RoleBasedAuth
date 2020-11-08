package com.qwertyfox.thefox.controller;

import com.qwertyfox.thefox.dao.AppUserDetailsDao;
import com.qwertyfox.thefox.model.FoxUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SecurityController {


    private final AppUserDetailsDao appUserDetailsDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityController(AppUserDetailsDao appUserDetailsDao, PasswordEncoder passwordEncoder) {
        this.appUserDetailsDao = appUserDetailsDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String loadAdmin() {
        return "secured/adminPage";
    }


    @PostMapping("/admin/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewUser(String firstName,
                           String lastName,
                           Integer contact,
                           String assignedRole) {

        int a = appUserDetailsDao.insertUser(firstName,lastName,contact);

        if(a == 1){ // successful insertion
            FoxUserDetails newUser = appUserDetailsDao.findLastRecord();
            int newUserKey = newUser.getUserID();

            Map<String, Integer> roleToInt = new HashMap<>();
            roleToInt.put("Admin", 1);
            roleToInt.put("Staff",2);
            roleToInt.put("Visitor",3);

            appUserDetailsDao.insertUserRole(newUserKey, roleToInt.get(assignedRole));

            String newUsername = newUser.getFirst_name();
            String newPassword = passwordEncoder.encode(newUsername.toLowerCase()+"123");

            appUserDetailsDao.insertUsernamePassword(newUserKey, newUsername, newPassword);
        }
        return "secured/adminPage";
    }


}
