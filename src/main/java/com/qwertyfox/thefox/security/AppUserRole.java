package com.qwertyfox.thefox.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.qwertyfox.thefox.security.AppUserPermissions.*;

public enum AppUserRole {

    ADMIN(Sets.newHashSet(DATABASE_READ,
            DATABASE_WRITE,
            USER_READ,
            USER_WRITE)),
    STAFF(Sets.newHashSet(DATABASE_READ,
            USER_READ)),
    VISITOR(Sets.newHashSet());

    private final Set<AppUserPermissions> appUserPermissions;

    AppUserRole(Set<AppUserPermissions> appUserPermissions) {
        this.appUserPermissions = appUserPermissions;
    }

    public Set<AppUserPermissions> getAppUserPermissions() {
        return appUserPermissions;
    }

    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getAppUserPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

        return authorities;
    }

}
