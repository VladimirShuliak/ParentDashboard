package com.varteq.parent.dashboard.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum RoleName implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_USER,
    ROLE_STUDENT,
    ROLE_PARENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
