package com.varteq.parent.dashboard.security;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum RoleName {

    ROLE_ADMIN,
    ROLE_USER,
    ROLE_STUDENT,
    ROLE_PARENT;
}
