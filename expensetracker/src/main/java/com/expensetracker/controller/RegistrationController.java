package com.expensetracker.controller;

import com.expensetracker.model.User;
import com.expensetracker.repository.UserRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Show Register Page
    @GetMapping("/register-page")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle Form Submission
    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("user") @Valid User user,
            BindingResult result) {

        if (result.hasErrors()) {
            return "register"; // back to form with errors
        }

        // Secure the password and assign default role
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        // Persist
        userRepository.save(user);

        // Redirect to login with a success flag
        return "redirect:/login?success";
    }
}
