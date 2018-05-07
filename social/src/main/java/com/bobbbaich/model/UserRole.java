package com.bobbbaich.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by bpogo on 9/8/2017.
 */
public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_MESSENGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
