package com.varteq.parent.dashboard.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String getAll(){
        return "users";
    }
}
