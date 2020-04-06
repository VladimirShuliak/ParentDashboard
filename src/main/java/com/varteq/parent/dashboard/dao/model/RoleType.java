package com.varteq.parent.dashboard.dao.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
    STUDENT, USER, ROLE_ADMIN, PARENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
