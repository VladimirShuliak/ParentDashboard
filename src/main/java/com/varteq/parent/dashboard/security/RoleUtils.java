package com.varteq.parent.dashboard.security;

import java.util.List;

import static com.varteq.parent.dashboard.security.RoleName.ROLE_ADMIN;

public class RoleUtils {

    public static boolean hasAdminRole(List<String> roleList) {
        return ifContains(roleList, ROLE_ADMIN);
    }

    private static boolean ifContains(List<String> roleList, RoleName role) {
        return roleList.contains(role.toString());
    }
}
