package com.swoqe.librarianship.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomePageController {

    @GetMapping("/")
    private String getWelcomePage() {
        return "welcome";
    }
}
