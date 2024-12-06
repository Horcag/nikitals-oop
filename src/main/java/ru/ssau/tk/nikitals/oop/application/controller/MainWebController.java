package ru.ssau.tk.nikitals.oop.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainWebController {
    @GetMapping("/")
    public String redirectToMain() {
        return "redirect:/functions/main";
    }

}
