package com.qwertyfox.thefox.security;

import com.qwertyfox.thefox.dao.AppUserRolesDao;
import com.qwertyfox.thefox.model.AppUsernamePassword;
import com.qwertyfox.thefox.model.FoxUsersRolesAndDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;



public class FoxUserDetails implements UserDetails {

    private final AppUsernamePassword appUsernamePassword;
    private final AppUserRolesDao appUserRolesDao;
    private final FoxUsersRolesAndDetails foxUsers;


    public FoxUserDetails(AppUsernamePassword appUsernamePassword, AppUserRolesDao appUserRolesDao) {
        this.appUsernamePassword = appUsernamePassword;
        this.appUserRolesDao = appUserRolesDao;
        foxUsers = findFoxUser(appUsernamePassword.getUserID());
    }


    private FoxUsersRolesAndDetails findFoxUser (int id){
        return appUserRolesDao.findByUserID(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String role = foxUsers.getRole_name();
        return Collections.singleton(
                new SimpleGrantedAuthority("ROLE_"+role));
    }

    @Override
    public String getPassword() {
        return appUsernamePassword.getPassword();
    }

    @Override
    public String getUsername() {
        return appUsernamePassword.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
