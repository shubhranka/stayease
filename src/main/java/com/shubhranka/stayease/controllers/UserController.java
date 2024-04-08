package com.shubhranka.stayease.controllers;

import com.shubhranka.stayease.entities.User;
import com.shubhranka.stayease.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public String register(@RequestBody User user) {
        return userService.registerUser(user);
    }


//    @GetMapping("/register")
//    public String register() {
//        return "Register";
//    }
}
