package com.hanbaguni.project.global.auth;

public enum Roles {
    GUEST("GUEST"),
    VERIFIED("VERIFIED"),
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
