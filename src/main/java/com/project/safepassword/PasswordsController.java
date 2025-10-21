package com.project.safepassword;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordsController {
    private final PasswordGenerator passwordGenerator;

    public PasswordsController(PasswordGenerator passwordGenerator) {
        this.passwordGenerator = passwordGenerator;
    }

    @GetMapping("/")
    public String showSafePassword() {
        return "index";
    }

    @PostMapping("/generate")
    public String generateSafePassword(@RequestParam(defaultValue = "12") Integer length,
                                       @RequestParam(required = false) boolean useNumbers,
                                       @RequestParam(required = false) boolean useSpecialChar,
                                       Model model) {

        try {
            String password = passwordGenerator.generatePassword(length, useNumbers, useSpecialChar);
            model.addAttribute("showMyPassword", password);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "index";
    }

}
