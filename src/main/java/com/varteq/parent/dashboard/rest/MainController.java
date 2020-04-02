package com.varteq.parent.dashboard.rest;

import com.varteq.parent.dashboard.model.UserEntity;
import com.varteq.parent.dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping
    public void main(@AuthenticationPrincipal UserEntity user) {

//        userService.loadByMail(user.getEmail());

    }
}
