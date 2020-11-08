package com.qwertyfox.thefox.security;

public enum AppUserPermissions {

    DATABASE_READ("database:read"),
    DATABASE_WRITE("database:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");


    private final String permission;

    AppUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
