package com.springsecurityauth.springsecurityauthservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/")
    public String hello() {
        return "<h1>Greetings !!</h1>";
    }

    @GetMapping("/user")
    public String helloUser() {
        return "<h1>Welcome User !!</h1>";
    }

    @GetMapping("/admin")
    public String helloAdmin() {
        return "<h1>Welcome Admin !!</h1>";
    }

}
