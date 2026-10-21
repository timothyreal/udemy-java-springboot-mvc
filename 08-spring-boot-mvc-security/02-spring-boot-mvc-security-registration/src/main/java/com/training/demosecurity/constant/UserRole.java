package com.training.demosecurity.constant;

public enum UserRole {
    EMPLOYEE("EMPLOYEE"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private String role;

    private UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return "ROLE_" + this.role;
    }

    public String getAuthority() {
        return this.role;
    }
}
