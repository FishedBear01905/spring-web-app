package com.example.springwebapp.controller;

import com.example.springwebapp.model.GambleUser;
import com.example.springwebapp.service.GambleUserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final GambleUserService gambleUserService;

    public UserController(
        GambleUserService gambleUserService
    ) {
        this.gambleUserService = gambleUserService;
    }

    @GetMapping("/user/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new GambleUser());
        return "user/user-create";
    }

    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute GambleUser user) {
        gambleUserService.save(user);
        return "redirect:/room/list"; 
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password, HttpSession session, Model model) {
        GambleUser user = gambleUserService.findByUserNameAndPassword(userName, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user); 
            return "home"; 
        } else {
            model.addAttribute("error", "ユーザー名またはパスワードが間違っています");
            return "user/login"; 
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/room/list"; 
    }

}