package com.practice.invoicingapp.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(ApplicationUserPermission.INVOICE_READ,ApplicationUserPermission.INVOICE_WRITE,ApplicationUserPermission.USER_READ,ApplicationUserPermission.USER_WRITE)),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.INVOICE_READ,ApplicationUserPermission.USER_READ,ApplicationUserPermission.USER_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuth(){
        Set <SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissions())).collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
                return permissions;
    }
}
