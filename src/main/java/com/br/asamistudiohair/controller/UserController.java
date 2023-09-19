package com.br.asamistudiohair.controller;

import com.br.asamistudiohair.model.User;
import com.br.asamistudiohair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
public class UserController {
    Logger logger = Logger.getLogger("LOG");
    public UserDetails getCurrentUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/public/register-form")
    public String showRegistrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration-form";
    }

    @PostMapping ("/public/register")
    public String registerUser(@ModelAttribute("user") @Valid User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.saveUser(user);
        return "registered-user-redirect-to-login";
    }

    @GetMapping("/public/registered-user-redirect-to-login")
    public String userRegisteredRedirectToLogin(Model model){
        return "redirect:/public/login";
    }

    @GetMapping("/public/login")
    public String showLoginForm(Model model){
        logger.log(Level.INFO, "login form");
        return "login";
    }

    @PostMapping("/user/verify-user")
    public String verifyUser(@Autowired HttpServletRequest request) {
        logger.log(Level.INFO, "verify");
        String email = request.getParameter("email");
        logger.log(Level.INFO, email);
        String password = request.getParameter("password");
        User user = userService.findByPasswordAndEmail(password, email);
        if (user != null) {
            return "redirect:/user/profile";
        }
        return "login";
    }

    @GetMapping("/user/profile")
    public String showProfile(Model model){
        UserDetails userDetails = getCurrentUserDetails();
        if (userDetails != null){
            model.addAttribute("user", userDetails);
            return "profile";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/user/logout")
    public String logoutUser(Model model){
        String name = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            name = ((UserDetails) principal).getUsername();
        }

        model.addAttribute("name", name);
        return "logout";
    }
}