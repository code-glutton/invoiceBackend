package com.practice.invoicingapp.security;

public enum ApplicationUserPermission {
    INVOICE_READ("invoice:read"),
    INVOICE_WRITE("invoice:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");
    private final String permissions;

    ApplicationUserPermission(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}
