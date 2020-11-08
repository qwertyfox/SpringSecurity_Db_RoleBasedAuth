package com.qwertyfox.thefox.controller;

import com.qwertyfox.thefox.dao.AppUserVerificationDao;
import com.qwertyfox.thefox.model.AppUsernamePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PasswordChangeController {

    private final AppUserVerificationDao appUserVerificationDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordChangeController(AppUserVerificationDao appUserVerificationDao,
                                    PasswordEncoder passwordEncoder) {
        this.appUserVerificationDao = appUserVerificationDao;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("changeReq")
    public String loadChangePassword() {
        return "secured/changePassword";
    }

    @PostMapping("/change")
    public String changePassword(String username,
                                 String oldPassword,
                                 String newPassword1,
                                 String newPassword2) {

        boolean matches = checkPassword(username ,oldPassword);

        if(matches){

            if(newPassword1.equals(newPassword2)){
                boolean check = changePassword(username, newPassword1);

                if(check){
                    return "secured/adminPage";
                }else {
                    return "secured/changePassword";
                }

            }else{
                return "secured/changePassword";
            }
        }
        else{
            return "secured/changePassword";
        }
    }

    private boolean checkPassword (String username, String password) {

        Optional<AppUsernamePassword> dataModel = appUserVerificationDao.findByUsername(username);
        dataModel.orElseThrow(() ->
                new UsernameNotFoundException("Error error on the code"));

        String oldPassword = dataModel.get().getPassword();
        return  passwordEncoder.matches(password, oldPassword);
    }

    private boolean changePassword(String username, String newPassword) {
        String hashPassword = passwordEncoder.encode(newPassword);
        int check = appUserVerificationDao.updatePassword(hashPassword,username);
        if(check==0){
            return false;
        }
        return true;
    }



}
